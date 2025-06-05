package com.atenea.backend.mapper;

import com.atenea.backend.dto.PedidoDto;
import com.atenea.backend.dto.PedidoProductoDto;
import com.atenea.backend.dto.ProductoResumenDto;
import com.atenea.backend.model.Pedido;
import com.atenea.backend.model.PedidoProducto;
import com.atenea.backend.model.Producto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Componente Mapper para convertir entidades de pedidos a DTOs
 */
@Component
public class PedidoMapper {

    /**
     * Convierte un Pedido entity a PedidoDto
     * @param pedido Entidad Pedido de la base de datos
     * @return PedidoDto con información completa del pedido
     */
    public PedidoDto toDto(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        // Convierte todos los productos del pedido a DTOs
        List<PedidoProductoDto> productosDto = pedido.getProductos() != null
                ? pedido.getProductos().stream()
                .map(this::toPedidoProductoDto)
                .collect(Collectors.toList())
                : List.of();

        // Crea el DTO con toda la información del pedido
        return new PedidoDto(
                pedido.getId(),
                pedido.getIdUsuario(),
                pedido.getFechaPedido(),
                pedido.getMetodoPago(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getDireccionEnvio(),
                productosDto
        );
    }

    /**
     * Convierte un PedidoProducto entity a PedidoProductoDto
     * @param pedidoProducto Producto específico dentro de un pedido
     * @return PedidoProductoDto con información del producto y su estado en el pedido
     */
    public PedidoProductoDto toPedidoProductoDto(PedidoProducto pedidoProducto) {
        if (pedidoProducto == null) {
            return null;
        }

        // Convierte la información del producto a resumen
        ProductoResumenDto productoResumen = pedidoProducto.getProducto() != null
                ? toProductoResumenDto(pedidoProducto.getProducto())
                : null;

        // Crea el DTO con cantidad, precio y datos del producto
        return new PedidoProductoDto(
                pedidoProducto.getId(),
                pedidoProducto.getIdPedido(),
                pedidoProducto.getIdProducto(),
                pedidoProducto.getCantidad(),
                pedidoProducto.getPrecioUnitario(),
                productoResumen
        );
    }

    /**
     * Convierte un Producto entity a ProductoResumenDto
     * @param producto Entidad Producto completa
     * @return ProductoResumenDto con información básica del producto
     */
    public ProductoResumenDto toProductoResumenDto(Producto producto) {
        if (producto == null) {
            return null;
        }

        // Extrae solo la información esencial del producto
        return new ProductoResumenDto(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getUrlImg()
        );
    }

    /**
     * Convierte una lista de Pedidos a DTOs
     * @param pedidos Lista de entidades Pedido
     * @return Lista de PedidoDto convertidos
     */
    public List<PedidoDto> toDtoList(List<Pedido> pedidos) {
        if (pedidos == null) {
            return List.of();
        }

        // Convierte cada pedido individualmente a DTO
        return pedidos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
