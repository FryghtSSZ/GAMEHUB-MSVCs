package com.duoc.gamehub.payment.controller;

import com.duoc.gamehub.payment.model.dto.PagoRequestDTO;
import com.duoc.gamehub.payment.model.entity.Pago;
import com.duoc.gamehub.payment.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping("/procesar")
    public ResponseEntity<Pago> procesarPago(@Valid @RequestBody PagoRequestDTO request) {
        return new ResponseEntity<>(pagoService.procesarPago(request), HttpStatus.CREATED);
    }

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<Pago> consultarPorOrden(@PathVariable Long ordenId) {
        return ResponseEntity.ok(pagoService.consultarPagoPorOrden(ordenId));
    }
}