package com.atenea.backend.dto;

import java.math.BigDecimal;

public record ProductosDto(
       String nombre,
       String descripcion,
       BigDecimal precio,
       String urlImg
) {
}
