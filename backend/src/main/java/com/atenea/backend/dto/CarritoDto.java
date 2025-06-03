package com.atenea.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO para representar un carrito completo con sus productos
 */
public record CarritoDto(
        UUID id,
        UUID idUsuario,
        LocalDateTime creado,
        LocalDateTime actualizado,
        List<CarritoProductoDto> productos,
        BigDecimal total,
        Integer totalItems
) {}