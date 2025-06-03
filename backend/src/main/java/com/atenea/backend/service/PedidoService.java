package com.atenea.backend.service;

import com.atenea.backend.dto.CrearPedidoDto;
import com.atenea.backend.dto.PedidoDto;
import com.atenea.backend.mapper.PedidoMapper;
import com.atenea.backend.model.*;
import com.atenea.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoService {

    private final UsuarioRepository usuarioRepository;
    private final PedidoRepository pedidoRepository;
    private final PedidoProductoRepository pedidoProductoRepository;
    private final CarritoRepository carritoRepository;
    private final CarritoProductoRepository carritoProductoRepository;
    private final PedidoMapper pedidoMapper;

    /**
     * Crear un pedido a partir del carrito actual del usuario
     */
    @Transactional
    public PedidoDto crearPedido(UUID idUsuario, CrearPedidoDto dto) {
        // Obtener datos del usuario
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener el carrito del usuario
        Carrito carrito = carritoRepository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Obtener los productos del carrito
        List<CarritoProducto> productosCarrito = carritoProductoRepository.findByIdCarrito(carrito.getId());

        if (productosCarrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        // Calcular el total del pedido
        BigDecimal total = calcularTotal(productosCarrito);

        // Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setIdUsuario(idUsuario);
        pedido.setMetodoPago(dto.metodoPago());
        pedido.setDireccionEnvio(usuario.getDireccion());
        pedido.setTotal(total);
        pedido.setEstado(EstadoPedido.PENDIENTE);

        // Guardar el pedido
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // Crear los productos del pedido basados en el carrito
        for (CarritoProducto carritoProducto : productosCarrito) {
            PedidoProducto pedidoProducto = new PedidoProducto(
                    pedidoGuardado.getId(),
                    carritoProducto.getIdProducto(),
                    carritoProducto.getCantidad(),
                    carritoProducto.getProducto().getPrecio()
            );
            pedidoProductoRepository.save(pedidoProducto);
        }

        // Vaciar el carrito después de crear el pedido
        carritoProductoRepository.deleteByIdCarrito(carrito.getId());

        // Recargar el pedido con todos los productos
        Pedido pedidoCompleto = pedidoRepository.findById(pedidoGuardado.getId())
                .orElse(pedidoGuardado);

        return pedidoMapper.toDto(pedidoCompleto);
    }

    /**
     * Obtener todos los pedidos de un usuario
     */
    @Transactional(readOnly = true)
    public Page<PedidoDto> obtenerPedidosUsuario(UUID idUsuario, Pageable pageable) {
        Page<Pedido> pedidos = pedidoRepository.findByIdUsuarioOrderByFechaPedidoDesc(idUsuario, pageable);
        return pedidos.map(pedidoMapper::toDto);
    }

    /**
     * Obtener un pedido específico por ID, verificando que pertenezca al usuario
     */
    @Transactional(readOnly = true)
    public PedidoDto obtenerPedidoPorId(UUID idPedido, UUID idUsuario) {
        Pedido pedido = pedidoRepository.findByIdAndIdUsuario(idPedido, idUsuario)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        return pedidoMapper.toDto(pedido);
    }

    /**
     * Cancelar un pedido (solo si está en estado PENDIENTE)
     */
    @Transactional
    public PedidoDto cancelarPedido(UUID idPedido, UUID idUsuario) {
        Pedido pedido = pedidoRepository.findByIdAndIdUsuario(idPedido, idUsuario)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            throw new RuntimeException("Solo se pueden cancelar pedidos en estado PENDIENTE");
        }

        pedido.setEstado(EstadoPedido.CANCELADO);
        Pedido pedidoCancelado = pedidoRepository.save(pedido);

        return pedidoMapper.toDto(pedidoCancelado);
    }

    /**
     * Calcular el total del pedido basado en los productos del carrito
     */
    private BigDecimal calcularTotal(List<CarritoProducto> productosCarrito) {
        return productosCarrito.stream()
                .map(cp -> cp.getProducto().getPrecio().multiply(BigDecimal.valueOf(cp.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Actualizar estado de un pedido (para administradores)
     */
    @Transactional
    public PedidoDto actualizarEstadoPedido(UUID idPedido, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado(nuevoEstado);
        Pedido pedidoActualizado = pedidoRepository.save(pedido);

        return pedidoMapper.toDto(pedidoActualizado);
    }
}
