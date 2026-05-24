package com.duoc.gamehub.users.service;

import com.duoc.gamehub.users.model.dto.DireccionRequestDTO;
import com.duoc.gamehub.users.model.dto.UsuarioRequestDTO;
import com.duoc.gamehub.users.model.entity.Direccion;
import com.duoc.gamehub.users.model.entity.Usuario;
import com.duoc.gamehub.users.repository.DireccionRepository;
import com.duoc.gamehub.users.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DireccionRepository direccionRepository;

    public Usuario crearUsuario(UsuarioRequestDTO dto) {
        log.info("Procesando creación de usuario con email: {}", dto.getEmail());

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            log.warn("Intento de registro con email duplicado: {}", dto.getEmail());
            throw new RuntimeException("El email ya se encuentra registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(dto.getRol());
        usuario.setEstado(true);

        Usuario guardado = usuarioRepository.save(usuario);
        log.info("Usuario creado exitosamente con ID: {}", guardado.getId());
        return guardado;
    }

    public List<Usuario> listarTodos() {
        log.info("Consultando listado de usuarios");
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        log.info("Buscando usuario por ID: {}", id);
        return usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado con ID: {}", id);
                    return new RuntimeException("Usuario no encontrado");
                });
    }

    public void desactivarUsuario(Long id) {
        log.info("Desactivando usuario ID: {}", id);
        Usuario usuario = buscarPorId(id);
        usuario.setEstado(false);
        usuarioRepository.save(usuario);
        log.info("Usuario ID: {} desactivado", id);
    }

    public Direccion agregarDireccion(DireccionRequestDTO dto) {
        log.info("Agregando dirección para el usuario ID: {}", dto.getUsuarioId());

        buscarPorId(dto.getUsuarioId());

        Direccion direccion = new Direccion();
        direccion.setUsuarioId(dto.getUsuarioId());
        direccion.setComuna(dto.getComuna());
        direccion.setCiudad(dto.getCiudad());
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());

        Direccion guardada = direccionRepository.save(direccion);
        log.info("Dirección registrada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Direccion> listarDireccionesPorUsuario(Long usuarioId) {
        log.info("Consultando direcciones del usuario ID: {}", usuarioId);
        return direccionRepository.findByUsuarioId(usuarioId);
    }
}