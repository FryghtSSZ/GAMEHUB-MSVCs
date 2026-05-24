package com.duoc.gamehub.inventory.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventarios")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "producto_id")
    private Long productoId;

    @Column(nullable = false, name = "stock_disponible")
    private Integer stockDisponible;

    @Column(nullable = false, name = "stock_reservado")
    private Integer stockReservado;

    @Column(nullable = false, name = "stock_minimo")
    private Integer stockMinimo;

    @Column(length = 100)
    private String ubicacion;
}