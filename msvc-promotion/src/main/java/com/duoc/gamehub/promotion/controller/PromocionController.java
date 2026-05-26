package com.duoc.gamehub.promotion.controller;

import com.duoc.gamehub.promotion.model.dto.PromocionRequestDTO;
import com.duoc.gamehub.promotion.model.entity.Promocion;
import com.duoc.gamehub.promotion.service.PromocionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/promociones")
@RequiredArgsConstructor
public class PromocionController {

    private final PromocionService promocionService;

    @PostMapping
    public ResponseEntity<Promocion> crearPromocion(@Valid @RequestBody PromocionRequestDTO request) {
        return new ResponseEntity<>(promocionService.crearPromocion(request), HttpStatus.CREATED);
    }

    @GetMapping("/validar/{codigo}")
    public ResponseEntity<Map<String, Double>> validarCodigo(@PathVariable String codigo) {
        Map<String, Double> response = new HashMap<>();
        response.put("porcentajeDescuento", promocionService.validarCodigo(codigo));
        return ResponseEntity.ok(response);
    }
}