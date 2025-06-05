package com.atenea.backend.repository;

import com.atenea.backend.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio para operaciones CRUD de la entidad Carrito
 */
@Repository
public interface CarritoRepository extends JpaRepository<Carrito, UUID> {

    /**
     * Busca un carrito por su identificador único
     */
    Optional<Carrito> findById(UUID uuid);

    /**
     * Busca un carrito por el identificador del usuario
     */
    Optional<Carrito> findByIdUsuario(UUID idUsuario);
}
