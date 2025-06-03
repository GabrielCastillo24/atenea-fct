package com.atenea.backend.mapper;

import com.atenea.backend.dto.CarritoDto;
import com.atenea.backend.dto.CarritoProductoDto;
import com.atenea.backend.dto.ResumenCarritoDto;
import com.atenea.backend.model.Carrito;
import com.atenea.backend.model.CarritoProducto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CarritoMapper {

    /**
     * Convierte una entidad Carrito a CarritoDto
     */
    public CarritoDto toDto(Carrito carrito) {
        if (carrito == null) {
            return null;
        }

        List<CarritoProductoDto> productosDto = carrito.getProductos() != null
                ? carrito.getProductos().stream()
                .map(this::toCarritoProductoDto)
                .toList()
                : List.of();

        BigDecimal total = calcularTotal(productosDto);
        Integer totalItems = calcularTotalItems(productosDto);

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
     */
    public CarritoProductoDto toCarritoProductoDto(CarritoProducto carritoProducto) {
        if (carritoProducto == null || carritoProducto.getProducto() == null) {
            return null;
        }

        BigDecimal subtotal = carritoProducto.getProducto().getPrecio()
                .multiply(BigDecimal.valueOf(carritoProducto.getCantidad()));

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
     */
    public ResumenCarritoDto toResumenDto(Carrito carrito) {
        if (carrito == null) {
            return null;
        }

        List<CarritoProductoDto> productosDto = carrito.getProductos() != null
                ? carrito.getProductos().stream()
                .map(this::toCarritoProductoDto)
                .toList()
                : List.of();

        BigDecimal total = calcularTotal(productosDto);
        Integer totalItems = calcularTotalItems(productosDto);

        return new ResumenCarritoDto(
                carrito.getId(),
                totalItems,
                total
        );
    }

    /**
     * Calcula el total del carrito
     */
    private BigDecimal calcularTotal(List<CarritoProductoDto> productos) {
        return productos.stream()
                .map(CarritoProductoDto::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calcula el total de items en el carrito
     */
    private Integer calcularTotalItems(List<CarritoProductoDto> productos) {
        return productos.stream()
                .mapToInt(CarritoProductoDto::cantidad)
                .sum();
    }
}

