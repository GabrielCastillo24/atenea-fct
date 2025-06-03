package com.atenea.backend.controller;

import com.atenea.backend.dto.*;
import com.atenea.backend.service.CarritoService;
import com.atenea.backend.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;
    private final UsuarioService usuarioService;

    /**
     * Obtiene el carrito completo del usuario autenticado
     */
    @GetMapping
    public ResponseEntity<CarritoDto> obtenerCarrito(Authentication authentication) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            CarritoDto carrito = carritoService.obtenerCarrito(idUsuario);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Obtiene el resumen del carrito (para header/navbar)
     */
    @GetMapping("/resumen")
    public ResponseEntity<ResumenCarritoDto> obtenerResumenCarrito(Authentication authentication) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            ResumenCarritoDto resumen = carritoService.obtenerResumenCarrito(idUsuario);
            return ResponseEntity.ok(resumen);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Agrega un producto al carrito
     */
    @PostMapping("/agregar")
    public ResponseEntity<CarritoDto> agregarProducto(
            @Valid @RequestBody AgregarCarritoDto dto,
            Authentication authentication) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            CarritoDto carrito = carritoService.agregarProducto(idUsuario, dto);
            return ResponseEntity.ok(carrito);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Actualiza la cantidad de un producto en el carrito
     */
    @PutMapping("/producto/{idProducto}/cantidad")
    public ResponseEntity<CarritoDto> actualizarCantidad(
            @PathVariable Integer idProducto,
            @Valid @RequestBody ActualizarCantidadCarritoDto dto,
            Authentication authentication) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            CarritoDto carrito = carritoService.actualizarCantidad(idUsuario, idProducto, dto);
            return ResponseEntity.ok(carrito);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Elimina un producto del carrito usando path variable (alternativa)
     */
    @DeleteMapping("/quitar-producto/{idProducto}")
    public ResponseEntity<CarritoDto> eliminarProductoPorId(
            @PathVariable Integer idProducto,
            Authentication authentication) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            CarritoDto carrito = carritoService.eliminarProducto(idUsuario, idProducto);
            return ResponseEntity.ok(carrito);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Vacía completamente el carrito
     */
    @DeleteMapping("/vaciar")
    public ResponseEntity<CarritoDto> vaciarCarrito(Authentication authentication) {
        try {
            UUID idUsuario = usuarioService.getUserByCorreo(authentication.getName()).getId();
            CarritoDto carrito = carritoService.vaciarCarrito(idUsuario);
            return ResponseEntity.ok(carrito);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
