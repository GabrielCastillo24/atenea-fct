package com.atenea.backend.repository;

import com.atenea.backend.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, UUID> {
    Optional<Carrito> findById(UUID uuid);
    Optional<Carrito> findByIdUsuario(UUID idUsuario);
}
