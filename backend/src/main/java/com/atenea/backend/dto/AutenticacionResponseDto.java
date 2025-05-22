package com.atenea.backend.dto;

import java.util.UUID;

public record AutenticacionResponseDto(
        String token,
        UUID refreshToken
) {
}
