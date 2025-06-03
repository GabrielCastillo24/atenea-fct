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

@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    /**
     * Crear un nuevo pedido a partir del carrito actual
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
