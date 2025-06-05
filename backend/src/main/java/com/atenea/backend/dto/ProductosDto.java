package com.atenea.backend.dto;

import java.math.BigDecimal;

/**
 * DTO Record para mostrar información completa de productos
 */
public record ProductosDto(
        Integer Id,
        String nombre,
        String descripcion,
        BigDecimal precio,
        String urlImg
) {
}
