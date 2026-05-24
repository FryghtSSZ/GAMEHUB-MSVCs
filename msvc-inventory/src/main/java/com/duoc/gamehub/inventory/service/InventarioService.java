package com.duoc.gamehub.inventory.service;

import com.duoc.gamehub.inventory.client.ProductoClient;
import com.duoc.gamehub.inventory.model.dto.InventarioRequestDTO;
import com.duoc.gamehub.inventory.model.dto.ReservaStockDTO;
import com.duoc.gamehub.inventory.model.entity.Inventario;
import com.duoc.gamehub.inventory.model.entity.MovimientoInventario;
import com.duoc.gamehub.inventory.repository.InventarioRepository;
import com.duoc.gamehub.inventory.repository.MovimientoInventarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final MovimientoInventarioRepository movimientoRepository;
    private final ProductoClient productoClient;

    public Inventario crearRegistro(InventarioRequestDTO dto) {
        log.info("Iniciando creación de inventario para producto ID: {}", dto.getProductoId());

        try {
            productoClient.buscarPorId(dto.getProductoId());
        } catch (Exception e) {
            log.error("Fallo de validación, producto ID: {}", dto.getProductoId());
            throw new RuntimeException("El producto ingresado no existe");
        }

        if (inventarioRepository.findByProductoId(dto.getProductoId()).isPresent()) {
            log.warn("Registro duplicado detectado para producto ID: {}", dto.getProductoId());
            throw new RuntimeException("El producto ya cuenta con un registro de inventario");
        }

        Inventario inventario = new Inventario();
        inventario.setProductoId(dto.getProductoId());
        inventario.setStockDisponible(dto.getStockDisponible());
        inventario.setStockReservado(0);
        inventario.setStockMinimo(dto.getStockMinimo());
        inventario.setUbicacion(dto.getUbicacion());

        Inventario guardado = inventarioRepository.save(inventario);
        registrarMovimiento(guardado.getProductoId(), "INGRESO_INICIAL", guardado.getStockDisponible());

        log.info("Inventario registrado exitosamente para producto ID: {}", guardado.getProductoId());
        return guardado;
    }

    public List<Inventario> listarTodos() {
        log.info("Consultando inventario global");
        return inventarioRepository.findAll();
    }

    public Inventario buscarPorProductoId(Long productoId) {
        log.info("Consultando inventario específico para producto ID: {}", productoId);
        return inventarioRepository.findByProductoId(productoId)
                .orElseThrow(() -> {
                    log.error("Inventario no encontrado para producto ID: {}", productoId);
                    return new RuntimeException("No existe registro de inventario para este producto");
                });
    }

    public Inventario reservarStock(ReservaStockDTO dto) {
        log.info("Procesando reserva de {} unidades para producto ID: {}", dto.getCantidad(), dto.getProductoId());

        Inventario inventario = buscarPorProductoId(dto.getProductoId());

        if (inventario.getStockDisponible() < dto.getCantidad()) {
            log.warn("Stock insuficiente para cubrir reserva del producto ID: {}", dto.getProductoId());
            throw new RuntimeException("Stock disponible insuficiente");
        }

        inventario.setStockDisponible(inventario.getStockDisponible() - dto.getCantidad());
        inventario.setStockReservado(inventario.getStockReservado() + dto.getCantidad());

        Inventario actualizado = inventarioRepository.save(inventario);
        registrarMovimiento(actualizado.getProductoId(), "RESERVA", dto.getCantidad());

        log.info("Reserva procesada exitosamente para producto ID: {}", dto.getProductoId());
        return actualizado;
    }

    private void registrarMovimiento(Long productoId, String tipo, Integer cantidad) {
        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProductoId(productoId);
        movimiento.setTipo(tipo);
        movimiento.setCantidad(cantidad);
        movimiento.setFecha(LocalDateTime.now());
        movimientoRepository.save(movimiento);
    }
}