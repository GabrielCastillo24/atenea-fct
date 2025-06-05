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

/**
 * Controlador REST que gestiona el registro de nuevos usuarios
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistroController {
    // Servicio para la lógica de registro de usuarios
    private final RegistroUsuarioService registroUsuarioService;
    // Mapper para convertir DTOs a entidades y viceversa
    private final UsuarioRegistroMapper usuarioRegistroMapper;
    /**
     * Registra un nuevo usuario en el sistema
     * @param registroRequestDto Datos del usuario a registrar (nombre, correo, contraseña, etc.)
     * @return RegistroResponseDto con la información del usuario registrado
     */
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
