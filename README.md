# 🛒 atenea-fct

Bienvenido/a a Atenea una tienda online enfocada en la venta de productos de papelería y material de oficina. Este proyecto ha sido desarrollado como parte de un entorno de prácticas formativas (FCT), y está diseñado para su implantación real en el mercado.

---

## 📌 Descripción del Proyecto

Esta aplicación ofrece una experiencia de compra completa y eficiente para el usuarios. Desde la visualización del catálogo hasta la gestión de pedidos y pagos, todo está diseñado para ofrecer una interfaz intuitiva y accesible.

**Características clave:**
- Navegación por categorías.
- Búsqueda de productos.
- Carrito de compras funcional.
- Registro e inicio de sesión de usuarios.
---

## 🖥️ Funcionalidades Principales

- ✅ **Diseño Responsive:** Accesible desde cualquier dispositivo (PC, tablet, móvil).
- 🛍️ **Visualización de Productos:** Cada artículo incluye nombre, precio, descripción y valoraciones.
- 🧺 **Carrito de Compras:** Añadir, modificar o eliminar productos fácilmente antes de pagar.
- 🔐 **Registro e Inicio de Sesión:** Requiere cuenta para completar el proceso de compra.
- 👤 **Usuarios Clientes:** Pueden explorar productos y comprarlos.

---

## ⚙️ Tecnologías Utilizadas

### 🧩 Frontend
- **Vue.js:** Framework JavaScript progresivo para interfaces reactivas.
- **Vite:** Herramienta de desarrollo rápida y ligera para el empaquetado.

### 🛠 Backend
- **Spring Boot (Java):** Framework robusto para el desarrollo de APIs RESTful.

### 🗄 Base de Datos
- **MariaDB:** Base de datos relacional de alto rendimiento basada en MySQL.

---

## 💻 Herramientas de Desarrollo

| Capa         | Herramienta       | Descripción                                              |
|--------------|-------------------|----------------------------------------------------------|
| Frontend     | Visual Studio Code| Editor de código con soporte para JavaScript y Vue.js    |
| Backend      | IntelliJ IDEA     | IDE optimizado para desarrollo con Java y Spring Boot    |
| Base de datos| DBeaver           | Cliente visual para gestionar bases de datos MariaDB     |

---

## ✍️ Autor
Desarrollado por [Gabriel Antonio Castillo Linares ](https://github.com/GabrielCastillo24)
---
## 🚀 Instalación y Uso

1. Clonar el repositorio con Git:
   ```bash
   git clone https://github.com/GabrielCastillo24/atenea-fct
   ```
2. Verificar que Docker esté instalado y en dado caso instalarlo.
   ```bash
   docker version
   ```
3. Crear la carpeta de la BBDD y arrancar el proyecto usando el fichero docker-compose.yml en la raíz del repositorio
   ```bash
   cd atenea-fct
   docker compose up
   ```
4. Acceder al servidor web desde http://localhost:8081
