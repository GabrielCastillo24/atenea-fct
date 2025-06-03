package com.atenea.backend.repository;

import com.atenea.backend.model.EstadoPedido;
import com.atenea.backend.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    /**
     * Encuentra todos los pedidos de un usuario ordenados por fecha descendente
     */
    Page<Pedido> findByIdUsuarioOrderByFechaPedidoDesc(UUID idUsuario, Pageable pageable);

    /**
     * Encuentra un pedido específico por ID y usuario
     */
    Optional<Pedido> findByIdAndIdUsuario(UUID id, UUID idUsuario);

    /**
     * Encuentra pedidos por estado
     */
    List<Pedido> findByEstado(EstadoPedido estado);

    /**
     * Encuentra pedidos por estado paginado
     */
    Page<Pedido> findByEstadoOrderByFechaPedidoDesc(EstadoPedido estado, Pageable pageable);

    /**
     * Encuentra pedidos de un usuario por estado
     */
    List<Pedido> findByIdUsuarioAndEstado(UUID idUsuario, EstadoPedido estado);

    /**
     * Encuentra pedidos en un rango de fechas
     */
    @Query("SELECT p FROM Pedido p WHERE p.fechaPedido BETWEEN :fechaInicio AND :fechaFin ORDER BY p.fechaPedido DESC")
    List<Pedido> findByFechaPedidoBetween(@Param("fechaInicio") LocalDateTime fechaInicio,
                                          @Param("fechaFin") LocalDateTime fechaFin);

    /**
     * Cuenta pedidos por estado
     */
    long countByEstado(EstadoPedido estado);

    /**
     * Cuenta pedidos de un usuario
     */
    long countByIdUsuario(UUID idUsuario);

    /**
     * Encuentra pedidos recientes (últimos 30 días)
     */
    @Query("SELECT p FROM Pedido p WHERE p.fechaPedido >= :fechaLimite ORDER BY p.fechaPedido DESC")
    List<Pedido> findPedidosRecientes(@Param("fechaLimite") LocalDateTime fechaLimite);
}
