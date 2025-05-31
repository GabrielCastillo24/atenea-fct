<template>
  <div class="contenedor-producto">
    <div
      class="tarjeta-producto"
      v-for="producto in productos"
      :key="producto.id"
    >
      <img
       :src="imagen"
        alt="Imagen del producto"
        class="imagen-producto"
      />
      <div class="contenido-producto">
        <h2 class="nombre-producto">{{ producto.nombre }}/ {{ producto.descripcion  }}</h2>
        <p class="precio-producto">{{ producto.precio }} €</p>
        <button class="añadir-carrito">Añadir al carrito</button>
      </div>
    </div>
  </div>
</template>

<script>
import iconoAtenea from '../../../assets/img/iconoAtenea.png'
import axios from 'axios';

export default {
  name: 'ProductoComponent',
  props: {
    producto: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      iconoAtenea,
      productos:[],
      imagen :'https://frikipolis.com/wp-content/uploads/2024/11/arcane-league-of-legends-pop-vinyl-figura-jinx-9cm1.jpg'
    };
  },
  mounted() {
    this.ObtenerProductos();
  },
  methods:{
   async ObtenerProductos() {
      try {
        const response = await axios.get('api/producto/obtener-productos');
        this.productos = response.data;
      } catch (error) {
        console.error('Error al obtener los productos:', error);
      }
    }
  },
};
</script>

<style scoped>
.contenedor-producto {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap:10px;
  padding: 1rem;
  justify-content: flex-start;
}

.tarjeta-producto {
  background-color: #fff;
  border: 1.5px solid #D9AF62;
  padding: 1rem;
  width: 220px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.tarjeta-producto:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 18px rgba(140, 91, 31, 0.25);
}

.imagen-producto {
  width: 100%;
  height: 140px;
  object-fit: contain;
  margin-bottom: 0.75rem;
}

.contenido-producto {
  text-align: center;
}

.nombre-producto {
  font-size: 1rem;
  font-weight: 600;
  color: #260B01;
  margin-bottom: 0.3rem;
}

.precio-producto {
  font-size: 0.95rem;
  color: #8C5B1F;
  margin-bottom: 0.75rem;
}

.añadir-carrito {
  background-color: #D9AF62;
  color: #260B01;
  border: none;
  padding: 0.4rem 0.9rem;
  font-size: 0.85rem;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 600;
  transition: background-color 0.3s ease;
}

.añadir-carrito:hover {
  background-color: #8C5B1F;
  color: #fff;
}
</style>
