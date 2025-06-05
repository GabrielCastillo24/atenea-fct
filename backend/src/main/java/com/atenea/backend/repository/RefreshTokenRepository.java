package com.atenea.backend.repository;

import com.atenea.backend.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio para operaciones CRUD de la entidad RefreshToken
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    /**
     * Busca un refresh token por ID que no haya expirado
     */
    Optional<RefreshToken> findByIdAndExpiracionAfter(UUID id, Instant date);
}