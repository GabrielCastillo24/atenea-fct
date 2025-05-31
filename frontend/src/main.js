import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // Tu instancia de Vue Router
import axios from 'axios';
import './assets/main.css'; 


//Configuracion Base de Axios 
const baseURL = 'http://localhost:8080'; // URL base de tu API
axios.defaults.baseURL = baseURL;

// Cargar el token JWT inicial si existe en localStorage
const initialToken = localStorage.getItem('jwt_token');
if (initialToken) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${initialToken}`;
  console.log('Token JWT cargado en Axios desde localStorage.');
  router.push('/inicio');
} else {
  console.log('No se encontró token JWT en localStorage al iniciar la app.');
}

// Función de Logout 
const logoutUser = () => {
  console.log('Cerrando sesión y redirigiendo al login.');

  const refreshToken = localStorage.getItem('refresh_token');
  const logoutAPIUrl = `/api/auth/logout`; // Relativo a baseURL
  if (refreshToken != null) {
      const response = axios.get(logoutAPIUrl, {
      params: { refreshToken: refreshToken }
    });
  }

  localStorage.removeItem('jwt_token');
  localStorage.removeItem('refresh_token');
  delete axios.defaults.headers.common['Authorization'];

  if (router.currentRoute.value.path !== '/login') {
    router.push('/login').catch(err => {
      if (err.name !== 'NavigationDuplicated') {
        console.error('Error al redirigir al login:', err);
      }
    });
  }
};

//Interceptor de Respuestas de Axios 
axios.interceptors.response.use(
  response => {
    // Si la respuesta es exitosa,la retornamos
    return response;
  },
  async error => {
    const originalRequest = error.config;

    // Verificamos si es un error 401, si no es una petición al endpoint de refresh,
    // y si no hemos intentado ya reintentar esta petición específica.
    if (error.response && error.response.status === 401 && originalRequest.url !== '/api/auth/refresh' && !originalRequest._retry) {
      originalRequest._retry = true; // Marcamos la petición para evitar bucles infinitos

      const refreshToken = localStorage.getItem('refresh_token');

      if (refreshToken) {
        console.log('Token JWT expirado. Intentando refrescar el token', refreshToken);
        try {
          const refreshAPIUrl = `/api/auth/refresh`; // Relativo a baseURL
          const response = await axios.get(refreshAPIUrl, {
            params: { refreshToken: refreshToken }
          });

          const newAccessToken = response.data.token;
          const newRefreshToken = response.data.refreshToken; //El backend podría devolver un nuevo refresh token

          if (!newAccessToken) {
            console.error('El endpoint de refresh no devolvió un nuevo token de acceso.');
            logoutUser();
            return Promise.reject(new Error('Fallo el refresco: no se obtuvo nuevo token.'));
          }

          console.log('Token refrescado exitosamente.');
          localStorage.setItem('jwt_token', newAccessToken);
          axios.defaults.headers.common['Authorization'] = `Bearer ${newAccessToken}`;
          originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`; // Actualizamos la cabecera de la petición original

          if (newRefreshToken) {
            localStorage.setItem('refresh_token', newRefreshToken);
            console.log('Nuevo refresh token guardado (simple).');
          }
          
          return axios(originalRequest); // Reintentamos la peticion original que fallo

        } catch (refreshError) {
          console.error('Error al intentar refrescar el token:', refreshError.response ? refreshError.response.data : refreshError.message);
          logoutUser(); // Si el refresh token es invalido o hay otro error, cerramos sesion
          return Promise.reject(refreshError); // Rechazamos con el error del refresco
        }
      } else {
        console.log('No se encontró refresh token. Cerrando sesión (simple).');
        logoutUser(); // Si no hay refresh token, cerramos sesión
        return Promise.reject(error); // Rechazamos con el error original
      }
    }

    // Si el error es un 401 y ya se intento (_retry=true) o es otro tipo de error,
    // o es un 401 del propio endpoint de refresh, lo propagamos.
    // Si fue un 401 de una petición reintentada, el _retry ya está en true, así que la proxima vez se rechazará directamente.
    if (error.response && error.response.status === 401 && originalRequest._retry) {
        console.warn('La petición reintentada con el nuevo token también falló con 401. Se requiere logout.');
        logoutUser();
    }

    return Promise.reject(error);
  }
);

// --- Inicialización de la App Vue ---
const app = createApp(App);
app.use(router);
// app.use(store); // Si usas Vuex/Pinia

app.mount('#app');