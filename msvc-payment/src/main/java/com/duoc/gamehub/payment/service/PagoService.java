package com.duoc.gamehub.payment.service;

import com.duoc.gamehub.payment.model.dto.PagoRequestDTO;
import com.duoc.gamehub.payment.model.entity.Pago;
import com.duoc.gamehub.payment.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;

    public Pago procesarPago(PagoRequestDTO dto) {
        log.info("Iniciando procesamiento de pago para orden ID: {} mediante {}", dto.getOrdenId(), dto.getMetodoPago());

        if (pagoRepository.existsByOrdenIdAndEstado(dto.getOrdenId(), "APROBADO")) {
            log.warn("La orden ID: {} ya registra un pago aprobado previo", dto.getOrdenId());
            throw new RuntimeException("La orden ingresada ya se encuentra pagada");
        }

        Pago pago = new Pago();
        pago.setOrdenId(dto.getOrdenId());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setFecha(LocalDateTime.now());

        // Simulando aprobación automática de la pasarela
        pago.setEstado("APROBADO");

        Pago guardado = pagoRepository.save(pago);
        log.info("Pago procesado y APROBADO exitosamente con ID de transacción: {}", guardado.getId());
        return guardado;
    }

    public Pago consultarPagoPorOrden(Long ordenId) {
        log.info("Consultando estado de pago para orden ID: {}", ordenId);
        return pagoRepository.findByOrdenId(ordenId)
                .orElseThrow(() -> {
                    log.error("No se encontraron registros de pago para la orden ID: {}", ordenId);
                    return new RuntimeException("No existe registro de pago para esta orden");
                });
    }
}