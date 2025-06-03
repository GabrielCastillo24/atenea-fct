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

@Component
public class PedidoMapper {

    /**
     * Convierte un Pedido entity a PedidoDto
     */
    public PedidoDto toDto(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        List<PedidoProductoDto> productosDto = pedido.getProductos() != null
                ? pedido.getProductos().stream()
                .map(this::toPedidoProductoDto)
                .collect(Collectors.toList())
                : List.of();

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
     */
    public PedidoProductoDto toPedidoProductoDto(PedidoProducto pedidoProducto) {
        if (pedidoProducto == null) {
            return null;
        }

        ProductoResumenDto productoResumen = pedidoProducto.getProducto() != null
                ? toProductoResumenDto(pedidoProducto.getProducto())
                : null;

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
     */
    public ProductoResumenDto toProductoResumenDto(Producto producto) {
        if (producto == null) {
            return null;
        }

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
     */
    public List<PedidoDto> toDtoList(List<Pedido> pedidos) {
        if (pedidos == null) {
            return List.of();
        }

        return pedidos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
