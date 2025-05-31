<template>
  <div class="inicio-container">
    <CabeceraComponent />
    <ProductoComponent />
    <PieDePaginaComponent />
  </div>
</template>

<script>
import CabeceraComponent from './cabecera/CabeceraComponent.vue';
import PieDePaginaComponent from './pieDePagina/PieDePaginaComponent.vue';
import ProductoComponent from './productos/ProductoComponent.vue';

export default {
  name: 'InicioComponent',
  components: {
    CabeceraComponent,
    PieDePaginaComponent,
    ProductoComponent
  }
};
</script>

<style scoped>
.inicio-container {
  background-image: url('/src/assets/img/iconoFondoInicio.png');
  background-size: 100px;
  background-position: center;
  min-height: 100vh;
}
</style>