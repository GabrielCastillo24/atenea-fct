# Etapa 1: Compilar la aplicación con Node.js
FROM node:lts-alpine AS builder

WORKDIR /app

# Copiar archivos necesarios
COPY package*.json ./
COPY vite.config.* ./
COPY . .

# Instalar dependencias
RUN npm ci

# Compilar el proyecto
RUN npm run build

# ------------------------------------------------

# Etapa 2: Imagen final con NGINX para servir archivos estáticos
FROM nginx:stable-alpine AS production

# Remover contenido por defecto
RUN rm -rf /usr/share/nginx/html/*

# Copiar archivos construidos desde el builder
COPY --from=builder /app/dist /usr/share/nginx/html

# Copiar configuración personalizada de NGINX para apps SPA
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponer puerto 80
EXPOSE 80

# Iniciar NGINX en primer plano
CMD ["nginx", "-g", "daemon off;"]