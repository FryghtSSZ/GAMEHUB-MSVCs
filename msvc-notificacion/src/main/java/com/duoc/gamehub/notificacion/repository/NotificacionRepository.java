package com.duoc.gamehub.notificacion.repository;

import com.duoc.gamehub.notificacion.model.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuarioIdOrderByFechaEmisionDesc(Long usuarioId);
}