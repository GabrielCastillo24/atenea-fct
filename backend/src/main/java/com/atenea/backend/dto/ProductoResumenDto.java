package com.atenea.backend.dto;

import java.math.BigDecimal;
/**
 * DTO Record para mostrar información resumida de un producto
 */
public record ProductoResumenDto(
        Integer id,
        String nombre,
        String descripcion,
        BigDecimal precio,
        String urlImg
) {}
