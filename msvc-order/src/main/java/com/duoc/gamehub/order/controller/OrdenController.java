package com.duoc.gamehub.order.controller;

import com.duoc.gamehub.order.model.dto.OrdenRequestDTO;
import com.duoc.gamehub.order.model.entity.Orden;
import com.duoc.gamehub.order.service.OrdenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordenes")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public ResponseEntity<Orden> crearOrden(@Valid @RequestBody OrdenRequestDTO request) {
        return new ResponseEntity<>(ordenService.crearOrden(request), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Orden>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ordenService.listarPorUsuario(usuarioId));
    }
}