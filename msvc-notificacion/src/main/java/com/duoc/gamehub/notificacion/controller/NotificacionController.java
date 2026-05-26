package com.duoc.gamehub.notificacion.controller;

import com.duoc.gamehub.notificacion.model.dto.NotificacionRequestDTO;
import com.duoc.gamehub.notificacion.model.entity.Notificacion;
import com.duoc.gamehub.notificacion.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@Valid @RequestBody NotificacionRequestDTO request) {
        return new ResponseEntity<>(notificacionService.crearNotificacion(request), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.listarPorUsuario(usuarioId));
    }
}