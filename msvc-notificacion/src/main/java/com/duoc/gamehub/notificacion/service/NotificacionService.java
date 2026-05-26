package com.duoc.gamehub.notificacion.service;

import com.duoc.gamehub.notificacion.model.dto.NotificacionRequestDTO;
import com.duoc.gamehub.notificacion.model.entity.Notificacion;
import com.duoc.gamehub.notificacion.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public Notificacion crearNotificacion(NotificacionRequestDTO dto) {
        log.info("Generando notificación tipo {} para usuario ID: {}", dto.getTipo(), dto.getUsuarioId());

        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(dto.getUsuarioId());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setTipo(dto.getTipo().toUpperCase());
        notificacion.setFechaEmision(LocalDateTime.now());

        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> listarPorUsuario(Long usuarioId) {
        log.info("Consultando historial de notificaciones para usuario ID: {}", usuarioId);
        return notificacionRepository.findByUsuarioIdOrderByFechaEmisionDesc(usuarioId);
    }
}