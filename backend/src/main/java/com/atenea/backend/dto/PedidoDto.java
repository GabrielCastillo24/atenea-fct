package com.atenea.backend.dto;

import com.atenea.backend.model.EstadoPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO para representar un pedido completo con sus productos
 */
public record PedidoDto(
        UUID id,
        UUID idUsuario,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime fechaPedido,
        String metodoPago,
        EstadoPedido estado,
        BigDecimal total,
        String direccionEnvio,
        List<PedidoProductoDto> productos
) {}