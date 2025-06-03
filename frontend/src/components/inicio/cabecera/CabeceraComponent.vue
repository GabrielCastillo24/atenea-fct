<template>
  <div>
    <header class="cabecera">
      <div class="logo">
        <img :src="iconoAtenea" alt="Logo Atenea" />
      </div>

      <div class="titulo">
        <h1>Atenea</h1>
      </div>

      <div class="iconos">
        <img :src="iconoUsuario" alt="Icono usuario" @click="mostrarMenuUsuario = true" />
        <img :src="iconoCarrito" alt="Icono carrito" @click="mostrarCarrito = true" />
      </div>
    </header>

    <!-- Fondo del carrito -->
    <transition name="desvanecer">
      <div v-if="mostrarCarrito" class="fondo-oscuro" @click="cerrarCarrito"></div>
    </transition>

    <!-- Carrito lateral -->
    <transition name="deslizar">
      <div v-if="mostrarCarrito" class="carrito-lateral">
        <button class="boton-cerrar" @click="cerrarCarrito">✕</button>
        <CarritoComponent />
      </div>
    </transition>

  
    <transition name="desvanecer">
      <div v-if="mostrarMenuUsuario" class="fondo-transparente" @click="cerrarMenuUsuario"></div>
    </transition>

    <!-- Menú usuario lateral -->
    <transition name="deslizar">
      <div v-if="mostrarMenuUsuario" class="menu-usuario-lateral">
        <button class="boton-cerrar" @click="cerrarMenuUsuario">✕</button>
        <ul class="lista-opciones">
          <li @click="irAPerfil">Perfil</li>
          <li @click="cerrarSesion">Cerrar sesión</li>
        </ul>
      </div>
    </transition>

   
    <transition name="desvanecer">
      <div v-if="mostrarPerfil" class="fondo-oscuro" @click="cerrarPerfil"></div>
    </transition>


    <transition name="deslizar">
      <div v-if="mostrarPerfil" class="panel-perfil">
        <button class="boton-cerrar" @click="cerrarPerfil">✕</button>
        <PerfilComponent />
        <PedidosComponent />
      </div>
    </transition>
  </div>
</template>

<script>
import iconoAtenea from '../../../assets/img/iconoAtenea.png'
import iconoUsuario from '../../../assets/img/usuario.png'
import iconoCarrito from '../../../assets/img/carrito.png'
import CarritoComponent from '../carrito/CarritoComponent.vue';
import PerfilComponent from '../perfil/PerfilComponent.vue';
import PedidosComponent from '../pedidos/PedidosComponent.vue';
import router from '../../../router';
import axios from 'axios';


export default {
  name: 'CabeceraComponent',
  components: {
    CarritoComponent,
    PerfilComponent,
    PedidosComponent

  },
  data() {
    return {
      iconoAtenea,
      iconoUsuario,
      iconoCarrito,
      mostrarCarrito: false,
      mostrarMenuUsuario: false,
      mostrarPerfil: false 
    }
  },
  methods: {
    cerrarCarrito() {
      this.mostrarCarrito = false
    },
    cerrarPerfil() {
      this.mostrarPerfil = false;
    },
    cerrarMenuUsuario() {
      this.mostrarMenuUsuario = false
    },
    irAPerfil() {
      this.mostrarPerfil = true;
      this.mostrarMenuUsuario = false; 
    },
    cerrarSesion() {
      try {
        const refreshToken = localStorage.getItem('refresh_token');
        axios.post(`/api/auth/logout?refreshToken=${refreshToken}`);
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
      } catch (error) {
        console.error('Error al cerrar sesión:', error);
      }
    }
  }
}
</script>

<style scoped>
.cabecera {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
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
.titulo {
  flex-grow: 1;
  text-align: center;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
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

.fondo-oscuro,
.fondo-transparente {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 999;
}

.fondo-oscuro {
  background-color: rgba(0, 0, 0, 0.5);
}

.fondo-transparente {
  background-color: rgba(0, 0, 0, 0.2);
}

.carrito-lateral {
  position: fixed;
  top: 0;
  right: 0;
  width: 50%;
  height: 100%;
  background-color: #F2F0E9;
  z-index: 1000;
  padding: 1rem;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.4);
  overflow-y: auto;
}

.panel-perfil {
  position: fixed;
  top: 0;
  right: 0;
  width: 70%;
  height: 100%;
  background-color: #F2F0E9;
  z-index: 1000;
  padding: 2rem;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.4);
  overflow-y: auto;
  font-family: Arial, sans-serif;
}

.menu-usuario-lateral {
  position: fixed;
  top: 0;
  right: 0;
  width: 220px;
  height: auto;
  background-color: #F2F0E9;
  z-index: 1000;
  padding: 1rem;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.4);
  border-bottom-left-radius: 10px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

.lista-opciones {
  list-style: none;
  padding: 0;
  margin: 2rem 0 0;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.lista-opciones li {
  cursor: pointer;
  padding: 0.5rem;
  border-radius: 5px;
  transition: background-color 0.2s;
}

.lista-opciones li:hover {
  background-color: #f0f0f0;
}

.boton-cerrar {
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  position: absolute;
  top: 1rem;
  right: 1rem;
}

.deslizar-enter-active,
.deslizar-leave-active {
  transition: transform 0.3s ease;
}

.deslizar-enter-from,
.deslizar-leave-to {
  transform: translateX(100%);
}

.deslizar-enter-to,
.deslizar-leave-from {
  transform: translateX(0%);
}

.desvanecer-enter-active,
.desvanecer-leave-active {
  transition: opacity 0.3s ease;
}

.desvanecer-enter-from,
.desvanecer-leave-to {
  opacity: 0;
}

.desvanecer-enter-to,
.desvanecer-leave-from {
  opacity: 1;
}
</style>
