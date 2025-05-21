package com.atenea.backend.controller;

import com.atenea.backend.dto.RegistroRequestDto;
import com.atenea.backend.dto.RegistroResponseDto;
import com.atenea.backend.mapper.UsuarioRegistroMapper;
import com.atenea.backend.service.RegistroUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistroController {

    private final RegistroUsuarioService registroUsuarioService;

    private final UsuarioRegistroMapper usuarioRegistroMapper;

    @PostMapping("/registro")
    public ResponseEntity<RegistroResponseDto> registrarUsuario(
            @Valid @RequestBody final RegistroRequestDto registroRequestDto) {

        final var usuarioRegistrado = registroUsuarioService
                .registroUsuario(usuarioRegistroMapper.toEntity(registroRequestDto));

        return ResponseEntity.ok(
                usuarioRegistroMapper.toRegistroResponseDto(usuarioRegistrado)
        );
    }
}
