package com.atenea.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para crear un nuevo pedido
 */
public record CrearPedidoDto(
        @NotNull(message = "El método de pago es obligatorio")
        @NotBlank(message = "El método de pago no puede estar vacío")
        String metodoPago
) {}
