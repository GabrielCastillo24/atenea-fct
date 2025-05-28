<template>
  <footer class="pie-de-pagina">
    <div class="contenido">
      <div class="logo">
        <img :src="iconoAtenea" alt="Logo Atenea" />
      </div> 
      
      <nav class="enlaces">
        <a href="">Inicio</a>
        <a href="">Perfil</a>
        <a href="">Contacta</a>
      </nav>
      
      <div class="iconos">
        <a href="https://github.com/GabrielCastillo24/atenea-fct" target="_blank" rel="noopener">
          <img :src="iconoGitHub" alt="icono GitHub" />
        </a>
        <a href="https://www.instagram.com/" target="_blank" rel="noopener">
          <img :src="iconoInstagram" alt="icono Instagram" />
        </a> 
        <a href="https://www.facebook.com/?locale=es_ES" target="_blank" rel="noopener">
          <img :src="iconoFacebook" alt="icono Facebook" />
        </a>
      </div>
    </div>
    <div class="copy">© 2025 Atenea. Todos los derechos reservados.</div>
  </footer>
</template>

<script>
import iconoAtenea from '../../../assets/img/iconoAtenea.png'
import iconoInstagram from '../../../assets/img/Instagram.png'
import iconoGitHub from '../../../assets/img/github.png'
import iconoFacebook from '../../../assets/img/Facebook.png'

export default {
  name: 'PieDePaginaComponent',
  data() {
    return {
      iconoAtenea,
      iconoInstagram,
      iconoGitHub,
      iconoFacebook
    };
  }
}
</script>

<style scoped>
.pie-de-pagina {
  background-color: #BF8B4B;
  color: white;
  padding: 2rem 1rem 1rem;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.contenido {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 1.5rem;
}

.logo img {
  width: 80px;
  border-radius: 10px;
  height: auto;
}

.enlaces {
  display: flex;
  gap: 2rem;
  flex-wrap: wrap;
  justify-content: center;
}

.enlaces a {
  color: white;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.3s ease;
}

.enlaces a:hover {
  color: #f0e4d7;
}

.iconos {
  display: flex;
  gap: 1.5rem;
  justify-content: center;
}

.iconos img {
  width: 30px;
  height: 30px;
  transition: transform 0.3s ease, opacity 0.3s ease;
}

.iconos a:hover img {
  transform: scale(1.2);
  opacity: 0.8;
}

.copy {
  margin-top: 2rem;
  font-size: 0.9rem;
  text-align: center;
  opacity: 0.85;
  width: 100%;
}

@media (max-width: 768px) {
  .contenido {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
}
</style>
