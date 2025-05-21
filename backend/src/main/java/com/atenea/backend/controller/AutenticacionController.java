package com.atenea.backend.controller;

import com.atenea.backend.dto.AutenticacionRequestDto;
import com.atenea.backend.dto.AutenticacionResponseDto;
import com.atenea.backend.service.AutenticacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}