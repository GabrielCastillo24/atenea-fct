package com.atenea.backend.dto;

public record AutenticacionRequestDto(
        String correo,
        String contrasena
) {
}
