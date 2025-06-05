package com.atenea.backend.dto;

import com.atenea.backend.model.EstadoPedido;
import jakarta.validation.constraints.NotNull;
/**
 * DTO Record para actualizar el estado de un pedido
 */
public record ActualizarEstadoPedidoDto(
        @NotNull(message = "El estado es obligatorio")
        EstadoPedido estado
) {}
