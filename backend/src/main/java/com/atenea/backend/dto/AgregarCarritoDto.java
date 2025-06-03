package com.atenea.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para agregar un producto al carrito
 */
public record AgregarCarritoDto(
        @NotNull(message = "El ID del producto es obligatorio")
        Integer idProducto,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser mayor a 0")
        Integer cantidad
) {}
