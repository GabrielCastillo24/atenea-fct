package com.atenea.backend.service;

import com.atenea.backend.dto.AutenticacionRequestDto;
import com.atenea.backend.dto.AutenticacionResponseDto;
import com.atenea.backend.model.RefreshToken;
import com.atenea.backend.model.Usuario;
import com.atenea.backend.repository.RefreshTokenRepository;
import com.atenea.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class AutenticacionService {

    @Value("${jwt.refresh-token-ttl}")
    private Duration refreshTokenTtl;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public AutenticacionResponseDto autenticarUsuario(
            final AutenticacionRequestDto request) {

        // Autenticamos el usuario
        final var autenticarUsuario = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (request.correo(), request.contrasena()));

        // Generamos el token JWT
        final var authToken = jwtService.generateToken(request.correo());

        // Recuperamos el usuario y generamos un refresh token
        Usuario usuario = usuarioRepository.findByCorreo(request.correo())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsuario(usuario);
        refreshToken.setExpiracion(Instant.now().plus(refreshTokenTtl));
        refreshTokenRepository.save(refreshToken);

        return new AutenticacionResponseDto(authToken, refreshToken.getId());
    }

    public AutenticacionResponseDto renovarToken(UUID refreshToken) {
        final var refreshTokenEntity = refreshTokenRepository
                .findByIdAndExpiracionAfter(refreshToken, Instant.now())
                .orElseThrow(() -> new BadCredentialsException("Refresh token inválido o expirado."));

        final var newAuthToken = jwtService
                .generateToken(refreshTokenEntity.getUsuario().getCorreo());
        return new AutenticacionResponseDto(newAuthToken, refreshToken);
    }

    public void revocarRefreshToken(UUID refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }
}
