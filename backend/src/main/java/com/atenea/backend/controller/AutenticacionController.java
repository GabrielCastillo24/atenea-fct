package com.atenea.backend.controller;

import com.atenea.backend.dto.AutenticacionRequestDto;
import com.atenea.backend.dto.AutenticacionResponseDto;
import com.atenea.backend.service.AutenticacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    // Servicio que contiene la lógica de autenticación
    private final AutenticacionService autenticacionService;
    /**
     * Endpoint para autenticar un usuario
     * @param autenticacionRequestDto Credenciales del usuario (correo y contraseña)
     * @return Token JWT y refresh token
     */
    @PostMapping("/login")
    public ResponseEntity<AutenticacionResponseDto> autenticarUsuario(
            @RequestBody final AutenticacionRequestDto autenticacionRequestDto
    ) {
        return ResponseEntity.ok(
                autenticacionService.autenticarUsuario(autenticacionRequestDto));
    }
    /**
     * Endpoint para renovar un token JWT expirado
     * @param refreshToken ID del refresh token válido
     * @return Nuevo token JWT con el mismo refresh token
     */
    @PostMapping("/refresh")
    public ResponseEntity<AutenticacionResponseDto> refreshToken(
            @RequestParam UUID refreshToken
    ) {
        AutenticacionResponseDto response =
                autenticacionService.renovarToken(refreshToken);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para cerrar sesión del usuario
     * @param refreshToken ID del refresh token a invalidar
     * @return Respuesta vacía (204 No Content)
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> revokeToken(@RequestParam UUID refreshToken) {
        autenticacionService.revocarRefreshToken(refreshToken);
        return ResponseEntity.noContent().build();
    }
}