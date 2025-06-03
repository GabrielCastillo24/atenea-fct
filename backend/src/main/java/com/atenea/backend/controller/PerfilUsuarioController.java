package com.atenea.backend.controller;


import com.atenea.backend.dto.PerfilUsuarioDto;
import com.atenea.backend.mapper.UsuarioMapper;
import com.atenea.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class PerfilUsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping("/me")
    public ResponseEntity<PerfilUsuarioDto> getPerfilUsuario(final Authentication authentication) {

        final var usuario = usuarioService.getUserByCorreo(authentication.getName());

        return ResponseEntity.ok(usuarioMapper.toPerfilUsuarioDto(usuario));
    }
}
