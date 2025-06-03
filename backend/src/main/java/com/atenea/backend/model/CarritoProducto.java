package com.atenea.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "carrito_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarritoProducto {

    @Id
    @UuidGenerator
    @Column(name = "id", length = 36)
    private UUID id;

    @Column(name = "id_carrito", length = 36, nullable = false)
    private UUID idCarrito;

    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @Column(nullable = false)
    private Integer cantidad = 1;

    @CreationTimestamp
    @Column
    private LocalDateTime agregado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrito", insertable = false, updatable = false)
    private Carrito carrito;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    // Constructor personalizado
    public CarritoProducto(UUID idCarrito, Integer idProducto, Integer cantidad) {
        this.idCarrito = idCarrito;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
}
