# Etapa 1: build de la app con Gradle
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copiar archivos de configuración de Gradle
COPY gradlew .
COPY gradle gradle

# Copiar el resto del proyecto
COPY . .

# Permisos para el wrapper
RUN chmod +x gradlew

# Compilar el proyecto
RUN ./gradlew clean bootJar

# Etapa 2: imagen final más liviana
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiar el jar compilado desde el contenedor builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto de la app
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]