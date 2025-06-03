package com.atenea.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para representar un producto dentro del carrito
 */
public record CarritoProductoDto(
        UUID id,
        UUID idCarrito,
        Integer idProducto,
        String nombreProducto,
        String descripcionProducto,
        String urlImg,
        BigDecimal precioUnitario,
        Integer cantidad,
        BigDecimal subtotal,
        LocalDateTime agregado
) {}