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
/**
 * Controlador REST que gestiona el perfil del usuario autenticado
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class PerfilUsuarioController {
    // Servicio para obtener información del usuario
    private final UsuarioService usuarioService;
    // Mapper para convertir entidades Usuario a DTOs
    private final UsuarioMapper usuarioMapper;

    /**
     * Obtiene el perfil completo del usuario autenticado
     * @param authentication Información del usuario autenticado desde el token JWT
     * @return PerfilUsuarioDto con los datos del perfil del usuario
     */
    @GetMapping("/me")
    public ResponseEntity<PerfilUsuarioDto> getPerfilUsuario(final Authentication authentication) {

        final var usuario = usuarioService.getUserByCorreo(authentication.getName());

        return ResponseEntity.ok(usuarioMapper.toPerfilUsuarioDto(usuario));
    }
}
