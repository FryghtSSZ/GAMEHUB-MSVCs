package com.duoc.gamehub.auth.service;

import com.duoc.gamehub.auth.client.UsuarioClient;
import com.duoc.gamehub.auth.model.dto.LoginRequestDTO;
import com.duoc.gamehub.auth.model.dto.RegistroCredencialDTO;
import com.duoc.gamehub.auth.model.entity.Credencial;
import com.duoc.gamehub.auth.repository.CredencialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final CredencialRepository credencialRepository;
    private final UsuarioClient usuarioClient;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Credencial registrarCredencial(RegistroCredencialDTO dto) {
        log.info("Procesando registro de credencial para email: {}", dto.getEmail());

        try {
            usuarioClient.buscarPorId(dto.getUsuarioId());
        } catch (Exception e) {
            log.error("Usuario no encontrado con ID: {}", dto.getUsuarioId());
            throw new RuntimeException("El usuario ingresado no existe en el sistema central");
        }

        if (credencialRepository.existsByEmail(dto.getEmail())) {
            log.warn("Intento de registro sobre email ya existente: {}", dto.getEmail());
            throw new RuntimeException("El email ya cuenta con credenciales activas");
        }

        Credencial credencial = new Credencial();
        credencial.setEmail(dto.getEmail());
        credencial.setPassword(passwordEncoder.encode(dto.getPassword()));
        credencial.setUsuarioId(dto.getUsuarioId());

        Credencial guardada = credencialRepository.save(credencial);
        log.info("Credencial registrada exitosamente para ID: {}", guardada.getId());
        return guardada;
    }

    public String login(LoginRequestDTO dto) {
        log.info("Procesando intento de login para email: {}", dto.getEmail());

        Credencial credencial = credencialRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> {
                    log.error("Credenciales no encontradas para email: {}", dto.getEmail());
                    return new RuntimeException("Credenciales inválidas");
                });

        if (!passwordEncoder.matches(dto.getPassword(), credencial.getPassword())) {
            log.warn("Fallo de autenticación por contraseña incorrecta para email: {}", dto.getEmail());
            throw new RuntimeException("Credenciales inválidas");
        }

        log.info("Autenticación exitosa para email: {}", dto.getEmail());
        return "TOKEN_SIMULADO_JWT_PARA_USUARIO_" + credencial.getUsuarioId();
    }
}