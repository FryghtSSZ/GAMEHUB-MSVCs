package com.duoc.gamehub.payment.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "orden_id")
    private Long ordenId;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false, name = "metodo_pago")
    private String metodoPago;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime fecha;
}