<template>
  <div class="perfil-component" v-for="item in usuario" :key="item.correo">
    <h2>Hola {{ item.nombre }} {{ item.apellido }}</h2>
    <p><strong>Usuario:</strong> {{ item.correo }}</p>
    <p><strong>Dirección:</strong> {{ item.direccion }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Perfil',
  data() {
    return {
      usuario: [], 
    };
  },
  mounted() {
    this.obtenerUsuario();
  },
  methods: {
    async obtenerUsuario() {
      try {
        const respuesta = await axios.get('api/user/me');
        this.usuario = [respuesta.data];
      } catch (error) {
        console.error('Error al obtener usuario:', error);
      }
    },
  },
};
</script>

<style scoped>
.perfil-component {
  background-color: #260B01;
  color: #D9AF62;
  padding: 2rem;
  border-radius: 0px;
  max-width: 1000px;
  margin: 2rem auto;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.perfil-component h2 {
  color: #D9AF62;
  font-size: 1.8rem;
  margin-bottom: 1rem;
}

.perfil-component p {
  font-size: 1.1rem;
  margin: 0.5rem 0;
  border-left: 4px solid #261B11;
  padding-left: 10px;
}
</style>