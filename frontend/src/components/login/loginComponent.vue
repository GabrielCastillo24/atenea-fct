<template>
  <div class="login-container">
    <form @submit.prevent="iniciarSesion" class="login-form">
      <div>
        <label for="correo">Correo:</label>
        <input id="correo" v-model="form.correo" type="email" required />
      </div>

      <div>
        <label for="contrasena">Contraseña:</label>
        <input id="contrasena" v-model="form.contrasena" type="password" required />
      </div>

      <div v-if="error" class="error-message">
        {{ error }}
      </div>

      <div class="botones">
        <button 
          type="submit"
          title="Iniciar Sesion"
        >
          Iniciar Sesión
        </button>
      </div>
      <p>¿No tienes cuenta? <router-link to="/registro">Registrate</router-link></p>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'LoginComponent',
  data() {
    return {
      form: {
        correo: '',
        contrasena: ''
      },
      error: ''
    };
  },
  methods: {
    iniciarSesion() {
      this.error = '';

      // Validar campos obligatorios
      if (!this.form.correo || !this.form.contrasena) {
        this.error = 'Por favor, rellena todos los campos obligatorios.';
        return;
      }

  // Crear JSON a enviar
  const payload = {
    correo: this.form.correo,
    contrasena: this.form.contrasena
  };

  try {
    const response = axios.post('/login', payload);
    console.log('Respuesta del servidor:', response.data);
    // Aquí puedes manejar el éxito, por ejemplo:
    // this.token = response.data.token; o redirigir al usuario
  } catch (error) {
    console.error('Error al iniciar sesión:', error);
    this.error = 'Credenciales incorrectas o error de conexión.';
  }
    },

  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1.5rem 0.5rem;
  min-height: 100vh;
  background-image: url('/src/assets/img/fondo.png');
  background-repeat: repeat;
  background-size: 150px 150px;
  background-color: #f5f5f5;
}

.login-form {
  max-width: 320px;
  width: 100%;
  background: white;
  border-radius: 12px;
  padding: 1rem 3.5rem;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.05);
  display: flex;
  flex-direction: column;
  gap: 0.8rem;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 0.85rem;
}

.login-form label {
  display: block;
  font-weight: 600;
  margin-bottom: 0.3rem;
  color: #333;
  font-size: 0.85rem;
}

.login-form input {
  width: 90%;
  padding: 0.75rem;
  border: 1.2px solid #ccc;
  border-radius: 8px;
  font-size: 0.85rem;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

.login-form input:focus {
  border-color: #3b82f6;
  box-shadow: 0 0 6px rgba(59, 130, 246, 0.15);
  outline: none;
}

.botones {
  display: flex;
  justify-content: space-between;
  margin-top: 0.7rem;
  gap: 0.5rem;
}

button {
  flex: 1;
  padding: 0.5rem 0.75rem;
  background-color: #4a3f35;
  color: white;
  font-weight: 600;
  font-size: 0.85rem;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #6b5648;
}

.error-message {
  font-weight: 600;
  font-size: 0.8rem;
  color: #d9534f;
  text-align: center;
  margin-top: 0.5rem;
}
</style>