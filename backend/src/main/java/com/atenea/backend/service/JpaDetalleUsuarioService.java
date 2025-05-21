package com.atenea.backend.service;

import com.atenea.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaDetalleUsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

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
