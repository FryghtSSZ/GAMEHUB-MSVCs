package com.duoc.gamehub.shipping.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "despachos")
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "orden_id", unique = true)
    private Long ordenId;

    @Column(nullable = false, name = "direccion_destino")
    private String direccionDestino;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false, name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}