package com.atenea.backend.service;

import com.atenea.backend.model.Carrito;
import com.atenea.backend.model.Usuario;
import com.atenea.backend.repository.CarritoRepository;
import com.atenea.backend.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistroUsuarioService {
    private final CarritoRepository carritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario registroUsuario(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new ValidationException("Correo ya registrado");
        }
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        Usuario usuarioRegistrado = usuarioRepository.save(usuario);

        Carrito carritoUsuario = new Carrito(usuarioRegistrado.getId());
        carritoRepository.save(carritoUsuario);

        return usuarioRegistrado;
    }
}
