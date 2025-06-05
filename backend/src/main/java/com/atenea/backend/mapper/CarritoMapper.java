package com.atenea.backend.mapper;

import com.atenea.backend.dto.CarritoDto;
import com.atenea.backend.dto.CarritoProductoDto;
import com.atenea.backend.dto.ResumenCarritoDto;
import com.atenea.backend.model.Carrito;
import com.atenea.backend.model.CarritoProducto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
/**
 * Componente Mapper para convertir entidades del carrito a DTOs
 */
@Component
public class CarritoMapper {

    /**
     * Convierte una entidad Carrito a CarritoDto
     * @param carrito Entidad Carrito de la base de datos
     * @return CarritoDto con información completa del carrito
     */
    public CarritoDto toDto(Carrito carrito) {
        if (carrito == null) {
            return null;
        }

        // Convierte los productos del carrito a DTOs
        List<CarritoProductoDto> productosDto = carrito.getProductos() != null
                ? carrito.getProductos().stream()
                .map(this::toCarritoProductoDto)
                .toList()
                : List.of();

        // Calcula el total y cantidad de items
        BigDecimal total = calcularTotal(productosDto);
        Integer totalItems = calcularTotalItems(productosDto);

        // Crea el DTO con toda la información del carrito
        return new CarritoDto(
                carrito.getId(),
                carrito.getIdUsuario(),
                carrito.getCreado(),
                carrito.getActualizado(),
                productosDto,
                total,
                totalItems
        );
    }

    /**
     * Convierte una entidad CarritoProducto a CarritoProductoDto
     * @param carritoProducto Producto dentro del carrito con cantidad
     * @return CarritoProductoDto con información del producto y subtotal
     */
    public CarritoProductoDto toCarritoProductoDto(CarritoProducto carritoProducto) {
        if (carritoProducto == null || carritoProducto.getProducto() == null) {
            return null;
        }

        // Calcula el subtotal multiplicando precio por cantidad
        BigDecimal subtotal = carritoProducto.getProducto().getPrecio()
                .multiply(BigDecimal.valueOf(carritoProducto.getCantidad()));

        // Crea el DTO con información del producto y cálculos
        return new CarritoProductoDto(
                carritoProducto.getId(),
                carritoProducto.getIdCarrito(),
                carritoProducto.getIdProducto(),
                carritoProducto.getProducto().getNombre(),
                carritoProducto.getProducto().getDescripcion(),
                carritoProducto.getProducto().getUrlImg(),
                carritoProducto.getProducto().getPrecio(),
                carritoProducto.getCantidad(),
                subtotal,
                carritoProducto.getAgregado()
        );
    }

    /**
     * Convierte un Carrito a ResumenCarritoDto (versión ligera)
     * @param carrito Entidad Carrito de la base de datos
     * @return ResumenCarritoDto con información básica para el header/navbar
     */
    public ResumenCarritoDto toResumenDto(Carrito carrito) {
        if (carrito == null) {
            return null;
        }

        // Convierte productos para calcular totales
        List<CarritoProductoDto> productosDto = carrito.getProductos() != null
                ? carrito.getProductos().stream()
                .map(this::toCarritoProductoDto)
                .toList()
                : List.of();

        // Calcula totales para el resumen
        BigDecimal total = calcularTotal(productosDto);
        Integer totalItems = calcularTotalItems(productosDto);

        // Devuelve solo la información esencial
        return new ResumenCarritoDto(
                carrito.getId(),
                totalItems,
                total
        );
    }

    /**
     * Calcula el total del carrito sumando todos los subtotales
     * @param productos Lista de productos en el carrito
     * @return BigDecimal con el total del carrito
     */
    private BigDecimal calcularTotal(List<CarritoProductoDto> productos) {
        return productos.stream()
                .map(CarritoProductoDto::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calcula el total de items en el carrito
     * @param productos Lista de productos en el carrito
     * @return Integer con la suma de todas las cantidades
     */
    private Integer calcularTotalItems(List<CarritoProductoDto> productos) {
        return productos.stream()
                .mapToInt(CarritoProductoDto::cantidad)
                .sum();
    }
}

