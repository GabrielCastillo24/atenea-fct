package com.atenea.backend.service;

import com.atenea.backend.dto.AutenticacionRequestDto;
import com.atenea.backend.dto.AutenticacionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AutenticacionResponseDto autenticarUsuario(
            final AutenticacionRequestDto request) {

        final var autenticacionToken = UsernamePasswordAuthenticationToken
                .unauthenticated(request.correo(), request.contrasena());

        final var autenticarUsuario = authenticationManager
                .authenticate(autenticacionToken);

        final var token = jwtService.generateToken(request.correo());
        return new AutenticacionResponseDto(token);
    }
}
