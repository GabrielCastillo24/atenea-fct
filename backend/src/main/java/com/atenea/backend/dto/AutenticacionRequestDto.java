package com.atenea.backend.dto;
/**
 * DTO Record para las peticiones de autenticación de usuarios
 */
public record AutenticacionRequestDto(
        String correo,
        String contrasena
) {
}
