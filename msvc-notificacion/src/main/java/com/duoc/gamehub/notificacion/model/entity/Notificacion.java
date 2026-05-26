package com.duoc.gamehub.notificacion.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "usuario_id")
    private Long usuarioId;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String tipo; // ej: "COMPRA_EXITOSA", "ENVIO_EN_CAMINO"

    @Column(nullable = false, name = "fecha_emision")
    private LocalDateTime fechaEmision;
}