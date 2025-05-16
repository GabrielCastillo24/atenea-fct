package com.atenea.backend.model.entity;

import jakarta.persistence.*;

import java.text.DecimalFormat;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private DecimalFormat precio;

    @Column(name = "stock")
    private Integer stock;
}
