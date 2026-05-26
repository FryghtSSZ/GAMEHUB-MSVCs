package com.duoc.gamehub.order.service;

import com.duoc.gamehub.order.client.InventarioClient;
import com.duoc.gamehub.order.client.ProductoClient;
import com.duoc.gamehub.order.client.UsuarioClient;
import com.duoc.gamehub.order.model.dto.OrdenRequestDTO;
import com.duoc.gamehub.order.model.entity.DetalleOrden;
import com.duoc.gamehub.order.model.entity.Orden;
import com.duoc.gamehub.order.repository.OrdenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;
    private final InventarioClient inventarioClient;

    public Orden crearOrden(OrdenRequestDTO dto) {
        log.info("Iniciando creación de orden para usuario ID: {}", dto.getUsuarioId());

        try {
            usuarioClient.buscarPorId(dto.getUsuarioId());
        } catch (Exception e) {
            log.error("Usuario no encontrado ID: {}", dto.getUsuarioId());
            throw new RuntimeException("El usuario ingresado no existe");
        }

        Orden orden = new Orden();
        orden.setUsuarioId(dto.getUsuarioId());
        orden.setFecha(LocalDateTime.now());
        orden.setEstado("PENDIENTE");
        orden.setDetalles(new ArrayList<>());

        double totalOrden = 0.0;

        for (var detalleDTO : dto.getDetalles()) {
            try {
                productoClient.buscarPorId(detalleDTO.getProductoId());
                inventarioClient.buscarPorProducto(detalleDTO.getProductoId());
            } catch (Exception e) {
                log.error("Error al validar producto o inventario para ID: {}", detalleDTO.getProductoId());
                throw new RuntimeException("Producto no existe o no tiene registro de inventario: " + detalleDTO.getProductoId());
            }

            DetalleOrden detalle = new DetalleOrden();
            detalle.setProductoId(detalleDTO.getProductoId());
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
            detalle.setSubtotal(detalleDTO.getCantidad() * detalleDTO.getPrecioUnitario());

            detalle.setOrden(orden);

            orden.getDetalles().add(detalle);
            totalOrden += detalle.getSubtotal();
        }

        orden.setTotal(totalOrden);
        Orden guardada = ordenRepository.save(orden);
        log.info("Orden creada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Orden> listarPorUsuario(Long usuarioId) {
        log.info("Consultando órdenes del usuario ID: {}", usuarioId);
        return ordenRepository.findByUsuarioId(usuarioId);
    }
}