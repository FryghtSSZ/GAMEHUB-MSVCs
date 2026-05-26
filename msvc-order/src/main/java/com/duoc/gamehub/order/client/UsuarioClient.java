package com.duoc.gamehub.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-users", url = "localhost:8081/api/v1/usuarios")
public interface UsuarioClient {

    @GetMapping("/{id}")
    Object buscarPorId(@PathVariable("id") Long id);
}