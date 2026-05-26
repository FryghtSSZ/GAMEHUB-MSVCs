package com.duoc.gamehub.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-inventory", url = "localhost:8084/api/v1/inventario")
public interface InventarioClient {

    @GetMapping("/producto/{productoId}")
    Object buscarPorProducto(@PathVariable("productoId") Long productoId);
}