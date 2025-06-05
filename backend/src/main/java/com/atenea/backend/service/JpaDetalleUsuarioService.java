package com.atenea.backend.service;

import com.atenea.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio para cargar detalles de usuario en el proceso de autenticación de Spring Security
 */
@Service
@RequiredArgsConstructor
public class JpaDetalleUsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Carga los detalles de un usuario por su correo electrónico para autenticación
     * @param correo Correo electrónico del usuario a autenticar
     * @return UserDetails con información del usuario para Spring Security
     */
    @Override
    public UserDetails loadUserByUsername(final String correo)
            throws UsernameNotFoundException {

        return usuarioRepository.findByCorreo(correo).map(usuario ->
                User.builder()
                        .username(correo)
                        .password(usuario.getContrasena())
                        .build()
        ).orElseThrow(() -> new UsernameNotFoundException(
                "No se encuentra el usuario con correo [%s].".formatted(correo)));
    }

}
