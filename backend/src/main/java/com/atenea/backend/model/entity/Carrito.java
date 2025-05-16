package com.atenea.backend.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "carrito")
public class Carrito {
    @Id
    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "idProducto")
    private Integer idProducto;

    @Column(name = "cantidadProducto")
    private Integer cantidadProducto;
}