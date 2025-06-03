package com.atenea.backend.repository;

import com.atenea.backend.model.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, UUID> {

    /**
     * Encuentra todos los productos de un carrito específico
     */
    List<CarritoProducto> findByIdCarrito(UUID idCarrito);

    /**
     * Encuentra un producto específico en un carrito específico
     */
    Optional<CarritoProducto> findByIdCarritoAndIdProducto(UUID idCarrito, Integer idProducto);

    /**
     * Elimina todos los productos de un carrito específico
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM CarritoProducto cp WHERE cp.idCarrito = :idCarrito")
    int deleteByIdCarrito(@Param("idCarrito") UUID idCarrito);

    /**
     * Elimina un producto específico de un carrito específico
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM CarritoProducto cp WHERE cp.idCarrito = :idCarrito AND cp.idProducto = :idProducto")
    int deleteByIdCarritoAndIdProducto(@Param("idCarrito") UUID idCarrito, @Param("idProducto") Integer idProducto);
}
