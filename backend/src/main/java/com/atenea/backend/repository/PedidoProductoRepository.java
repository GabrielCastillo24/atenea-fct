package com.atenea.backend.repository;

import com.atenea.backend.model.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, UUID> {

    /**
     * Encuentra todos los productos de un pedido específico
     */
    List<PedidoProducto> findByIdPedido(UUID idPedido);

    /**
     * Encuentra productos de pedidos por ID de producto
     */
    List<PedidoProducto> findByIdProducto(Integer idProducto);

    /**
     * Cuenta la cantidad total vendida de un producto específico
     */
    @Query("SELECT COALESCE(SUM(pp.cantidad), 0) FROM PedidoProducto pp WHERE pp.idProducto = :idProducto")
    Long countCantidadVendidaPorProducto(@Param("idProducto") Integer idProducto);

    /**
     * Encuentra los productos más vendidos
     */
    @Query("SELECT pp.idProducto, SUM(pp.cantidad) as totalVendido " +
            "FROM PedidoProducto pp " +
            "GROUP BY pp.idProducto " +
            "ORDER BY totalVendido DESC")
    List<Object[]> findProductosMasVendidos();

    /**
     * Elimina todos los productos de un pedido (útil para cancelaciones)
     */
    void deleteByIdPedido(UUID idPedido);
}
