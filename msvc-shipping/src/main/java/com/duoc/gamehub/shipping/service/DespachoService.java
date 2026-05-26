package com.duoc.gamehub.shipping.service;

import com.duoc.gamehub.shipping.client.UsuarioClient;
import com.duoc.gamehub.shipping.model.dto.DespachoRequestDTO;
import com.duoc.gamehub.shipping.model.entity.Despacho;
import com.duoc.gamehub.shipping.repository.DespachoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DespachoService {

    private final DespachoRepository despachoRepository;
    private final UsuarioClient usuarioClient;

    public Despacho crearDespacho(DespachoRequestDTO dto) {
        log.info("Generando logística de despacho para orden ID: {}", dto.getOrdenId());

        try {
            usuarioClient.buscarPorId(dto.getUsuarioId());
        } catch (Exception e) {
            log.error("Error al validar usuario ID: {}", dto.getUsuarioId());
            throw new RuntimeException("El usuario ingresado no existe en el sistema central");
        }

        if (despachoRepository.findByOrdenId(dto.getOrdenId()).isPresent()) {
            throw new RuntimeException("Ya existe un despacho en curso para esta orden");
        }

        Despacho despacho = new Despacho();
        despacho.setOrdenId(dto.getOrdenId());
        despacho.setDireccionDestino(dto.getDireccionDestino());
        despacho.setEstado("PREPARACION");
        despacho.setFechaActualizacion(LocalDateTime.now());

        return despachoRepository.save(despacho);
    }

    public Despacho actualizarEstado(Long ordenId, String nuevoEstado) {
        log.info("Actualizando estado de despacho para orden ID: {} a {}", ordenId, nuevoEstado);
        Despacho despacho = despachoRepository.findByOrdenId(ordenId)
                .orElseThrow(() -> new RuntimeException("No existe despacho para la orden indicada"));

        despacho.setEstado(nuevoEstado);
        despacho.setFechaActualizacion(LocalDateTime.now());
        return despachoRepository.save(despacho);
    }
}