package com.atenea.backend.dto;

import com.atenea.backend.model.EstadoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO ligero para listar pedidos sin el detalle de productos
 */
public record PedidoResumenDto(
        UUID id,
        LocalDateTime fechaPedido,
        String metodoPago,
        EstadoPedido estado,
        BigDecimal total,
        String direccionEnvio,
        Integer totalProductos
) {}