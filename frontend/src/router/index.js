// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import RegistroComponent from '../components/RegistroComponent/RegistroComponent.vue';
import LoginComponent from '../components/login/loginComponent.vue';

const routes = [
  { path: '/login', name: 'Login', component: LoginComponent },
  { path: '/registro', name: 'Registro', component: RegistroComponent },
  { path: '/', redirect: '/login' }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;