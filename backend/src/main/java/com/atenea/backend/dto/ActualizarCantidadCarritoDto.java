package com.atenea.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para actualizar la cantidad de un producto en el carrito
 */
public record ActualizarCantidadCarritoDto (
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    Integer cantidad
) {}
