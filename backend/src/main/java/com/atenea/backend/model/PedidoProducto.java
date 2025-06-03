package com.atenea.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "pedido_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProducto {

    @Id
    @UuidGenerator
    @Column(length = 36)
    private UUID id;

    @Column(name = "id_pedido", length = 36, nullable = false)
    private UUID idPedido;

    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    // Constructor personalizado
    public PedidoProducto(UUID idPedido, Integer idProducto, Integer cantidad, BigDecimal precioUnitario) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
}
