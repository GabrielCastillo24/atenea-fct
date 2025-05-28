<template>
  <div class="registro-container">
    <form @submit.prevent="registrar" class="registro-form">
      <div>
        <label for="nombre">Nombre:</label>
        <input id="nombre" v-model="form.nombre" type="text" required />
      </div>

      <div>
        <label for="apellido">Apellido:</label>
        <input id="apellido" v-model="form.apellido" type="text" required />
      </div>

      <div>
        <label for="correo">Correo:</label>
        <input id="correo" v-model="form.correo" type="email" required />
      </div>

      <div>
        <label for="telefono">Teléfono:</label>
        <input id="telefono" v-model="form.telefono" type="tel" required />
      </div>

      <div>
        <label for="direccion">Dirección:</label>
        <input id="direccion" v-model="form.direccion" type="text" />
      </div>

      <div>
        <label for="contrasena">Contraseña:</label>
        <input id="contrasena" v-model="form.contrasena" type="password" required />
      </div>

      <div>
        <label for="contrasena2">Repetir Contraseña:</label>
        <input id="contrasena2" v-model="form.contrasena2" type="password" required />
      </div>

      <div v-if="error" style="color: red; margin-top: 10px;">
        {{ error }}
      </div>
      <div class="botones">
          <button
              title="Borrar Todo"
              @click="borrarTodo"
              >
              Borrar todo
          </button>
          <button 
              type="submit"
              title="Registrar">
              Registrar
          </button>
      </div>
          <p>¿Ya tienes cuenta? <router-link to="/login"> Inicia sesión</router-link></p>
    </form>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  name: 'RegistroComponent',
  data() {
    return {
      form: {
        nombre: '',
        apellido: '',
        correo: '',
        telefono: '',
        direccion: '',
        contrasena: '',
        contrasena2: ''
      },
      error: ''
    };
  },
  methods: {
    registrar() {
         this.error = '';

      // Validación: campos obligatorios
      for (const campo in this.form) {
        if (
          ['nombre', 'apellido', 'correo', 'telefono', 'contrasena', 'contrasena2'].includes(campo) &&
          !this.form[campo]
        ) {
          this.error = 'Por favor, rellena todos los campos obligatorios.';
          return;
        }
      }

      // Validación de contraseñas si coinciden
      if (this.form.contrasena !== this.form.contrasena2) {
        this.error = 'Las contraseñas no coinciden.';
        return;
      }

      // Construir el JSON excluyendo 
      const payload = { ...this.form };
      delete payload.contrasena2;
      delete payload.codigoPostal;

      axios.post('/api/auth/registro', payload)
        .then(response => {
          console.log('Usuario registrado:', response.data);
          this.borrarTodo();
          this.$router.push('/login');
        })
        .catch(error => {
          console.error('Error al registrar:', error);
          this.error = 'Error al registrar el usuario.';
        });
      this.borrarTodo();
 
    },
    borrarTodo(){
              for (const campo in this.form) {
        this.form[campo] = '';
      }
      this.error = '';
    }
  }
};
</script>

<style scoped>
.registro-container {
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

.registro-form {
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

.registro-form label {
  display: block;
  font-weight: 600;
  margin-bottom: 0.3rem;
  color: #333;
  font-size: 0.85rem;
}

.registro-form input {
  width: 100%;
  padding: 0.4rem 0.75rem;
  border: 1.2px solid #ccc;
  border-radius: 8px;
  font-size: 0.85rem;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

.registro-form input:focus {
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

div[style] {
  font-weight: 600;
  font-size: 0.8rem;
  color: #d9534f;
  text-align: center;
  margin-top: 0.5rem;
}
</style>
