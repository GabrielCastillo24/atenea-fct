package com.atenea.backend.dto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO para representar un producto dentro de un pedido
 */
public record PedidoProductoDto(
        UUID id,
        UUID idPedido,
        Integer idProducto,
        Integer cantidad,
        BigDecimal precioUnitario,
        ProductoResumenDto producto // Información básica del producto
) {}
