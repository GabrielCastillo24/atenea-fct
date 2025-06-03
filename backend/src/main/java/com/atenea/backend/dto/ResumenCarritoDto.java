package com.atenea.backend.dto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO ligero para mostrar resumen del carrito (ej: en el header)
 */
public record ResumenCarritoDto(
        UUID id,
        Integer totalItems,
        BigDecimal total
) {}
