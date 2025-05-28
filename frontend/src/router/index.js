// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import RegistroComponent from '../components/RegistroComponent/RegistroComponent.vue';
import LoginComponent from '../components/login/LoginComponent.vue';
import InicioComponent from '../components/inicio/InicioComponent.vue';

const routes = [
  { path: '/login', name: 'Login', component: LoginComponent },
  { path: '/registro', name: 'Registro', component: RegistroComponent },
  { path: '/inicio', name: 'Inicio', component: InicioComponent },
  { path: '/', redirect: '/login' }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;