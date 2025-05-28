<template>
  <header class="cabecera">
    <div class="logo">
      <img :src="iconoAtenea" alt="Logo Atenea" />
    </div>

    <div class="barra-busqueda">
      <input type="text" placeholder="Buscar..." />
      <button title="Buscar">
        Buscar
      </button>
    </div>

    <div class="iconos">
      <img :src="iconoUsuario" alt="icono usuario" />
      <img :src="iconoCarrito" alt="icono carrito" />
    </div>
  </header>
</template>

<script>
import iconoAtenea from '../../../assets/img/iconoAtenea.png'
import iconoUsuario from '../../../assets/img/usuario.png'
import iconoCarrito from '../../../assets/img/carrito.png'

export default {
  name: 'CabeceraComponent',
  data() {
    return {
      iconoAtenea,
      iconoUsuario,
      iconoCarrito
    }
  }
}
</script>

<style scoped>
.cabecera {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #261B11;
  padding: 1rem 2rem;
  color: #D9AF62;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.4);
  flex-wrap: wrap; 
}

.logo img {
  height: 70px;
}

.barra-busqueda {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  max-width: 600px;
}

.barra-busqueda input {
  flex: 1;
  min-width: 200px;
  padding: 0.5rem 1rem;
  border: 2px solid #8C5B1F;
  border-radius: 5px;
  background-color: #fff;
  color: #260B01;
}

.barra-busqueda button {
  background-color: #D9AF62;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 5px;
  color: #260B01;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.barra-busqueda button:hover {
  background-color: #8C5B1F;
  color: white;
}

.iconos {
  display: flex;
  gap: 1rem;
  align-items: center;
}

.iconos img {
  height: 32px;
  width: 32px;
  cursor: pointer;
  transition: transform 0.2s ease;
  object-fit: contain;
}

.iconos img:hover {
  transform: scale(1.1);
}

</style>
