package com.atenea.backend.dto;
/**
 * DTO Record para mostrar la información del perfil del usuario
 */
public record PerfilUsuarioDto(
        String correo,
        String nombre,
        String apellido,
        String direccion
) {
}
