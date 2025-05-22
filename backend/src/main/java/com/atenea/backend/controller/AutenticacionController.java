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

    private final AutenticacionService autenticacionService;

    @PostMapping("/login")
    public ResponseEntity<AutenticacionResponseDto> autenticarUsuario(
            @RequestBody final AutenticacionRequestDto autenticacionRequestDto
    ) {
        return ResponseEntity.ok(
                autenticacionService.autenticarUsuario(autenticacionRequestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AutenticacionResponseDto> refreshToken(
            @RequestParam UUID refreshToken
    ) {
        AutenticacionResponseDto response =
                autenticacionService.renovarToken(refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> revokeToken(@RequestParam UUID refreshToken) {
        autenticacionService.revocarRefreshToken(refreshToken);
        return ResponseEntity.noContent().build();
    }
}