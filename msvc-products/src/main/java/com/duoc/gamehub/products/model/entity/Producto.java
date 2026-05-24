package com.duoc.gamehub.products.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false, name = "categoria_id")
    private Long categoriaId;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Boolean estado;
}