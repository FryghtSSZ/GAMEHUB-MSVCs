package com.duoc.gamehub.shipping.controller;

import com.duoc.gamehub.shipping.model.dto.DespachoRequestDTO;
import com.duoc.gamehub.shipping.model.entity.Despacho;
import com.duoc.gamehub.shipping.service.DespachoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/despachos")
@RequiredArgsConstructor
public class DespachoController {

    private final DespachoService despachoService;

    @PostMapping
    public ResponseEntity<Despacho> crearDespacho(@Valid @RequestBody DespachoRequestDTO request) {
        return new ResponseEntity<>(despachoService.crearDespacho(request), HttpStatus.CREATED);
    }

    @PatchMapping("/orden/{ordenId}/estado")
    public ResponseEntity<Despacho> actualizarEstado(@PathVariable Long ordenId, @RequestParam String estado) {
        return ResponseEntity.ok(despachoService.actualizarEstado(ordenId, estado));
    }
}