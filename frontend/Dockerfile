# Dockerfile
FROM node:21

# Set working directory
WORKDIR /

# Copiamos solo los archivos de dependencias para aprovechar la cache
COPY package*.json ./

# Instalamos dependencias
RUN npm install

# Copiamos el resto del proyecto
COPY . .

# Exponemos el puerto de desarrollo de Vite (por defecto)
EXPOSE 5173

# Comando por defecto: iniciar servidor de desarrollo
CMD ["npm", "run", "dev"]