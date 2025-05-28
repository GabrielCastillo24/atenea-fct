<template>
    <CabeceraComponent></CabeceraComponent>
    <h1>Hola estoy en inicio</h1>
    <PieDePaginaComponent></PieDePaginaComponent>>
</template>

<script>
import CabeceraComponent from './cabecera/CabeceraComponent.vue';
import PieDePaginaComponent from './pieDePagina/PieDePaginaComponent.vue';
export default {
  name: 'InicioComponent',
  components: {
   CabeceraComponent,
   PieDePaginaComponent
  },
  data() {
    return {
      
    };
  },
  methods: {
   
  }
};

</script>

<style>
</style>