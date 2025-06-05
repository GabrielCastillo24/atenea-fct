package com.atenea.backend.service;

import com.atenea.backend.model.Usuario;
import com.atenea.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.GONE;

/**
 * Servicio para gestionar operaciones de usuarios
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    /**
     * Obtiene un usuario por su correo electrónico
     * @param correo Correo electrónico del usuario a buscar
     * @return Usuario encontrado
     */
    public Usuario getUserByCorreo(final String correo) {
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(GONE,
                        "La cuenta de usuario ha sido eliminada o desactivada"));
    }
}