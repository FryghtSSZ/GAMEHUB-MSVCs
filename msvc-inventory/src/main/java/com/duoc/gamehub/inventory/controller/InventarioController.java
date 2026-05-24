package com.duoc.gamehub.inventory.controller;

import com.duoc.gamehub.inventory.model.dto.InventarioRequestDTO;
import com.duoc.gamehub.inventory.model.dto.ReservaStockDTO;
import com.duoc.gamehub.inventory.model.entity.Inventario;
import com.duoc.gamehub.inventory.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<Inventario> crear(@Valid @RequestBody InventarioRequestDTO request) {
        return new ResponseEntity<>(inventarioService.crearRegistro(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listarTodos());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> buscarPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(inventarioService.buscarPorProductoId(productoId));
    }

    @PostMapping("/reservar")
    public ResponseEntity<Inventario> reservar(@Valid @RequestBody ReservaStockDTO request) {
        return ResponseEntity.ok(inventarioService.reservarStock(request));
    }
}