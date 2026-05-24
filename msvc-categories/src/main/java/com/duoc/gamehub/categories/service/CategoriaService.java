package com.duoc.gamehub.categories.service;

import com.duoc.gamehub.categories.model.dto.CategoriaRequestDTO;
import com.duoc.gamehub.categories.model.entity.Categoria;
import com.duoc.gamehub.categories.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria crear(CategoriaRequestDTO dto) {
        log.info("Iniciando creación de categoría: {}", dto.getNombre());

        if (categoriaRepository.existsByNombre(dto.getNombre())) {
            log.warn("Intento de crear categoría con nombre duplicado: {}", dto.getNombre());
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setEstado(true);

        Categoria guardada = categoriaRepository.save(categoria);
        log.info("Categoría guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Categoria> listarTodas() {
        log.info("Consultando todas las categorías");
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        log.info("Buscando categoría por ID: {}", id);
        return categoriaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Categoría no encontrada con ID: {}", id);
                    return new RuntimeException("Categoría no encontrada");
                });
    }

    public Categoria actualizar(Long id, CategoriaRequestDTO dto) {
        log.info("Iniciando actualización de categoría ID: {}", id);
        Categoria categoria = buscarPorId(id);

        if (!categoria.getNombre().equals(dto.getNombre()) && categoriaRepository.existsByNombre(dto.getNombre())) {
            log.warn("Intento de actualizar categoría a nombre duplicado: {}", dto.getNombre());
            throw new RuntimeException("Ya existe otra categoría con ese nombre");
        }

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());

        Categoria actualizada = categoriaRepository.save(categoria);
        log.info("Categoría ID: {} actualizada exitosamente", id);
        return actualizada;
    }

    public void desactivar(Long id) {
        log.info("Procesando desactivación de categoría ID: {}", id);
        Categoria categoria = buscarPorId(id);
        categoria.setEstado(false);
        categoriaRepository.save(categoria);
        log.info("Categoría ID: {} desactivada exitosamente", id);
    }
}