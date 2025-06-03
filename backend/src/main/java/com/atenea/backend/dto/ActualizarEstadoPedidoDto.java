package com.atenea.backend.dto;

import com.atenea.backend.model.EstadoPedido;
import jakarta.validation.constraints.NotNull;

public record ActualizarEstadoPedidoDto(
        @NotNull(message = "El estado es obligatorio")
        EstadoPedido estado
) {}
