package com.duoc.gamehub.products.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-categories", url = "localhost:8082/api/v1/categorias")
public interface CategoriaClient {

    @GetMapping("/{id}")
    Object buscarPorId(@PathVariable("id") Long id);
}