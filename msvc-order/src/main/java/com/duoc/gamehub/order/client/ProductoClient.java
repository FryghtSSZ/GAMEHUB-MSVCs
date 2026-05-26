package com.duoc.gamehub.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-products", url = "localhost:8083/api/v1/productos")
public interface ProductoClient {

    @GetMapping("/{id}")
    Object buscarPorId(@PathVariable("id") Long id);
}