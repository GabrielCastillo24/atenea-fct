package com.atenea.backend.dto;

public record PerfilUsuarioDto(
        String correo,
        String nombre,
        String apellido,
        String direccion
) {
}
