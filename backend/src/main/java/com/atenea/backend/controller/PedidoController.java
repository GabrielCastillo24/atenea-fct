package com.atenea.backend.controller;

import com.atenea.backend.dto.CrearPedidoDto;
import com.atenea.backend.dto.PedidoDto;
import com.atenea.backend.service.PedidoService;
import com.atenea.backend.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
/**
 * Controlador REST que gestiona todas las operaciones relacionadas con pedidos
 */
@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class PedidoController {
    // Servicio para la lógica de pedidos
    private final PedidoService pedidoService;
    // Servicio para obtener información del usuario
    private final UsuarioService usuarioService;

    /**
     * Crear un nuevo pedido a partir del carrito actual
     * @param dto Datos del pedido (dirección de envío, método de pago, etc.)
     * @param authentication Información del usuario autenticado
     * @return PedidoDto con los detalles del pedido creado
     */
    @PostMapping("/crear")
    public ResponseEntity<PedidoDto> crearPedido(
            @Valid @RequestBody CrearPedidoDto dto,
            Authentication authentication) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            PedidoDto pedido = pedidoService.crearPedido(idUsuario, dto);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Obtener todos los pedidos del usuario autenticado
     * @param authentication Información del usuario autenticado
     * @param pageable Parámetros de paginación (página, tamaño, ordenación)
     * @return Page<PedidoDto> con el historial de pedidos paginado
     */
    @GetMapping("/mis-pedidos")
    public ResponseEntity<Page<PedidoDto>> obtenerMisPedidos(
            Authentication authentication,
            Pageable pageable) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            Page<PedidoDto> pedidos = pedidoService.obtenerPedidosUsuario(idUsuario, pageable);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Obtener un pedido específico por ID
     * @param idPedido UUID del pedido a consultar
     * @param authentication Información del usuario autenticado
     * @return PedidoDto con los detalles completos del pedido
     */
    @GetMapping("/{idPedido}")
    public ResponseEntity<PedidoDto> obtenerPedido(
            @PathVariable UUID idPedido,
            Authentication authentication) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            PedidoDto pedido = pedidoService.obtenerPedidoPorId(idPedido, idUsuario);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Cancelar un pedido (solo si está en estado PENDIENTE)
     * @param idPedido UUID del pedido a cancelar
     * @param authentication Información del usuario autenticado
     * @return PedidoDto actualizado con estado CANCELADO
     */
    @PutMapping("/{idPedido}/cancelar")
    public ResponseEntity<PedidoDto> cancelarPedido(
            @PathVariable UUID idPedido,
            Authentication authentication) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            PedidoDto pedido = pedidoService.cancelarPedido(idPedido, idUsuario);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
