package com.duoc.gamehub.products.service;

import com.duoc.gamehub.products.client.CategoriaClient;
import com.duoc.gamehub.products.model.dto.ProductoRequestDTO;
import com.duoc.gamehub.products.model.entity.Producto;
import com.duoc.gamehub.products.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaClient categoriaClient;

    public Producto crear(ProductoRequestDTO dto) {
        log.info("Iniciando creación de producto: {} - {} {}", dto.getNombre(), dto.getMarca(), dto.getModelo());

        try {
            categoriaClient.buscarPorId(dto.getCategoriaId());
        } catch (Exception e) {
            log.error("Fallo al validar la categoría ID: {}", dto.getCategoriaId());
            throw new RuntimeException("La categoría ingresada no existe");
        }

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setMarca(dto.getMarca());
        producto.setModelo(dto.getModelo());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoriaId(dto.getCategoriaId());
        producto.setDescripcion(dto.getDescripcion());
        producto.setEstado(true);

        Producto guardado = productoRepository.save(producto);
        log.info("Producto guardado exitosamente con ID: {}", guardado.getId());
        return guardado;
    }

    public List<Producto> listarTodos() {
        log.info("Consultando todos los productos del catálogo");
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        log.info("Buscando producto por ID: {}", id);
        return productoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Producto no encontrado con ID: {}", id);
                    return new RuntimeException("Producto no encontrado");
                });
    }

    public void desactivar(Long id) {
        log.info("Procesando desactivación lógica para el producto ID: {}", id);
        Producto producto = buscarPorId(id);
        producto.setEstado(false);
        productoRepository.save(producto);
        log.info("Producto ID: {} desactivado exitosamente", id);
    }
}