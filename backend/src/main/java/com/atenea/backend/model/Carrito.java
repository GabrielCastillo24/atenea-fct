package com.atenea.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carrito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {

    @Id
    @UuidGenerator
    @Column(length = 36)
    private UUID id;

    @Column(name = "id_usuario", length = 36, nullable = false, unique = true)
    private UUID idUsuario;

    @CreationTimestamp
    @Column
    private LocalDateTime creado;

    @UpdateTimestamp
    @Column
    private LocalDateTime actualizado;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarritoProducto> productos;

    // Constructor personalizado
    public Carrito(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}
