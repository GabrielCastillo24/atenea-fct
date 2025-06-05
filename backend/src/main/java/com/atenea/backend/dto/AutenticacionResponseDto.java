package com.atenea.backend.dto;

import java.util.UUID;
/**
 * DTO Record para las respuestas de autenticación exitosa
 */
public record AutenticacionResponseDto(
        String token,
        UUID refreshToken
) {
}
