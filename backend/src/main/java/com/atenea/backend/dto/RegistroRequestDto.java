package com.atenea.backend.dto;

public record RegistroRequestDto(
        String nombre,
        String apellido,
        String correo,
        String telefono,
        String direccion,
        String contrasena
) {
}
