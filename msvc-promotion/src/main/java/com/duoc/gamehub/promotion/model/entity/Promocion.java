package com.duoc.gamehub.promotion.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "promociones")
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false, name = "porcentaje_descuento")
    private Double porcentajeDescuento;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false, name = "fecha_expiracion")
    private LocalDate fechaExpiracion;
}