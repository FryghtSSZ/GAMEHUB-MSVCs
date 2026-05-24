package com.duoc.gamehub.users.controller;

import com.duoc.gamehub.users.model.dto.DireccionRequestDTO;
import com.duoc.gamehub.users.model.dto.UsuarioRequestDTO;
import com.duoc.gamehub.users.model.entity.Direccion;
import com.duoc.gamehub.users.model.entity.Usuario;
import com.duoc.gamehub.users.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioRequestDTO request) {
        return new ResponseEntity<>(usuarioService.crearUsuario(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/direcciones")
    public ResponseEntity<Direccion> agregarDireccion(@Valid @RequestBody DireccionRequestDTO request) {
        return new ResponseEntity<>(usuarioService.agregarDireccion(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/direcciones")
    public ResponseEntity<List<Direccion>> listarDirecciones(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.listarDireccionesPorUsuario(id));
    }
}