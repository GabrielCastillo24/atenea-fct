<template>
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
      <label for="codigoPostal">Código Postal:</label>
      <input id="codigoPostal" v-model="form.codigoPostal" type="text" />
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

  </form>
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
        codigoPostal: '',
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

      axios.post('http://localhost:8080/registro/registrar-usuario', payload)
        .then(response => {
          console.log('Usuario registrado:', response.data);
          this.borrarTodo();
        })
        .catch(error => {
          console.error('Error al registrar:', error);
          this.error = 'Error al registrar el usuario.';
        });
      // Limpieza tras éxito 
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
.registro-form {
  width: 450px;
  padding: 2rem 2.5rem;
  background: #f9fafb;
  box-shadow: 0 4px 12px rgb(0 0 0 / 0.1);
  border: 0.5px solid black;
  border-radius: 12px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.registro-form > div {
  margin-bottom: 1.25rem;
}

label {
  display: block;
  text-align: left;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: #333;
  user-select: none;
}

input {
  width: 100%;
  padding: 0.55rem 0.8rem;
  border: 2px solid #ddd;
  border-radius: 8px;
  font-size: 1rem;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
  outline-offset: 2px;
}

input:focus {
  border-color: #3b82f6;
  box-shadow: 0 0 8px rgba(59, 130, 246, 0.4);
}

button {
  width: 40%;
  margin: 10px;
  padding: 0.75rem 1rem;
  background-color: #714914;
  color: white;
  font-weight: 700;
  font-size: 1.1rem;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  user-select: none;
}

button:hover {
  background-color: #89622e;
}

div[style] {
  font-weight: 600;
  font-size: 0.95rem;
}
</style>
