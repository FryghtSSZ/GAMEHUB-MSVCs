package com.duoc.gamehub.auth.controller;

import com.duoc.gamehub.auth.model.dto.LoginRequestDTO;
import com.duoc.gamehub.auth.model.dto.RegistroCredencialDTO;
import com.duoc.gamehub.auth.model.entity.Credencial;
import com.duoc.gamehub.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<Credencial> registrar(@Valid @RequestBody RegistroCredencialDTO request) {
        return new ResponseEntity<>(authService.registrarCredencial(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequestDTO request) {
        Map<String, String> response = new HashMap<>();
        response.put("token", authService.login(request));
        return ResponseEntity.ok(response);
    }
}