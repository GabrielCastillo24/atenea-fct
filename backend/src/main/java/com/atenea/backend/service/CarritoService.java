package com.atenea.backend.service;

import com.atenea.backend.dto.*;
import com.atenea.backend.mapper.CarritoMapper;
import com.atenea.backend.model.Carrito;
import com.atenea.backend.model.CarritoProducto;
import com.atenea.backend.model.Producto;
import com.atenea.backend.repository.CarritoProductoRepository;
import com.atenea.backend.repository.CarritoRepository;
import com.atenea.backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final CarritoProductoRepository carritoProductoRepository;
    private final ProductoRepository productoRepository;
    private final CarritoMapper carritoMapper;

    /**
     * Obtiene o crea un carrito para el usuario
     */
    private Carrito obtenerOCrearCarrito(UUID idUsuario) {
        return carritoRepository.findByIdUsuario(idUsuario)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito(idUsuario);
                    return carritoRepository.save(nuevoCarrito);
                });
    }

    /**
     * Agrega un producto al carrito
     */
    public CarritoDto agregarProducto(UUID idUsuario, AgregarCarritoDto dto) {
        // Verificar que el producto existe
        Producto producto = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Carrito carrito = obtenerOCrearCarrito(idUsuario);

        // Buscar si el producto ya existe en el carrito
        Optional<CarritoProducto> productoExistente = carritoProductoRepository
                .findByIdCarritoAndIdProducto(carrito.getId(), dto.idProducto());

        if (productoExistente.isPresent()) {
            // Si existe, actualizar cantidad
            CarritoProducto carritoProducto = productoExistente.get();
            carritoProducto.setCantidad(carritoProducto.getCantidad() + dto.cantidad());
            carritoProductoRepository.save(carritoProducto);
        } else {
            // Si no existe, crear nuevo CarritoProducto
            CarritoProducto nuevoCarritoProducto = new CarritoProducto(
                    carrito.getId(),
                    dto.idProducto(),
                    dto.cantidad()
            );
            carritoProductoRepository.save(nuevoCarritoProducto);
        }

        // Recargar el carrito con los productos actualizados
        Carrito carritoActualizado = carritoRepository.findById(carrito.getId()).orElse(carrito);
        return carritoMapper.toDto(carritoActualizado);
    }

    /**
     * Actualiza la cantidad de un producto en el carrito
     */
    public CarritoDto actualizarCantidad(UUID idUsuario, Integer idProducto, ActualizarCantidadCarritoDto dto) {
        Carrito carrito = carritoRepository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        CarritoProducto carritoProducto = carritoProductoRepository
                .findByIdCarritoAndIdProducto(carrito.getId(), idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

        carritoProducto.setCantidad(dto.cantidad());
        carritoProductoRepository.save(carritoProducto);

        // Recargar el carrito con los productos actualizados
        Carrito carritoActualizado = carritoRepository.findById(carrito.getId()).orElse(carrito);
        return carritoMapper.toDto(carritoActualizado);
    }

    /**
     * Elimina un producto del carrito por ID - SOLUCIÓN DEFINITIVA
     */
    @Transactional
    public CarritoDto eliminarProducto(UUID idUsuario, Integer idProducto) {
        Carrito carrito = carritoRepository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Verificar que el producto existe en el carrito
        CarritoProducto carritoProducto = carritoProductoRepository
                .findByIdCarritoAndIdProducto(carrito.getId(), idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

        // Eliminar usando el método custom del repositorio
        carritoProductoRepository.deleteByIdCarritoAndIdProducto(carrito.getId(), idProducto);

        // Recargar el carrito con los productos actualizados
        Carrito carritoActualizado = carritoRepository.findById(carrito.getId()).orElse(carrito);
        return carritoMapper.toDto(carritoActualizado);
    }

    /**
     * Vacía completamente el carrito
     */
    public CarritoDto vaciarCarrito(UUID idUsuario) {
        Carrito carrito = carritoRepository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Verificar que el carrito tiene productos antes de vaciar
        List<CarritoProducto> productos = carritoProductoRepository.findByIdCarrito(carrito.getId());

        if (!productos.isEmpty()) {
            // Eliminar todos los productos del carrito usando el repositorio específico
            carritoProductoRepository.deleteByIdCarrito(carrito.getId());
        }

        // Recargar el carrito (ahora vacío)
        Carrito carritoActualizado = carritoRepository.findById(carrito.getId()).orElse(carrito);
        return carritoMapper.toDto(carritoActualizado);
    }

    /**
     * Obtiene el carrito completo del usuario
     */
    @Transactional(readOnly = true)
    public CarritoDto obtenerCarrito(UUID idUsuario) {
        Carrito carrito = carritoRepository.findByIdUsuario(idUsuario)
                .orElse(new Carrito(idUsuario)); // Devuelve carrito vacío si no existe

        return carritoMapper.toDto(carrito);
    }

    /**
     * Obtiene el resumen del carrito (para header/navbar)
     */
    @Transactional(readOnly = true)
    public ResumenCarritoDto obtenerResumenCarrito(UUID idUsuario) {
        Carrito carrito = carritoRepository.findByIdUsuario(idUsuario)
                .orElse(new Carrito(idUsuario)); // Devuelve carrito vacío si no existe

        return carritoMapper.toResumenDto(carrito);
    }
}