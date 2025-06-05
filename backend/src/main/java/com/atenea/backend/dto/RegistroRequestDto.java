package com.atenea.backend.dto;
/**
 * DTO Record para las peticiones de registro de nuevos usuarios
 */
public record RegistroRequestDto(
        String nombre,
        String apellido,
        String correo,
        String telefono,
        String direccion,
        String contrasena
) {
}
