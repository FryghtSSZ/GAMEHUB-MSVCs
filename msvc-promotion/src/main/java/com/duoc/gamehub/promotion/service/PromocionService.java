package com.duoc.gamehub.promotion.service;

import com.duoc.gamehub.promotion.model.dto.PromocionRequestDTO;
import com.duoc.gamehub.promotion.model.entity.Promocion;
import com.duoc.gamehub.promotion.repository.PromocionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromocionService {

    private final PromocionRepository promocionRepository;

    public Promocion crearPromocion(PromocionRequestDTO dto) {
        log.info("Creando nueva promoción con código: {}", dto.getCodigo());

        if (promocionRepository.findByCodigo(dto.getCodigo()).isPresent()) {
            throw new RuntimeException("El código de promoción ya existe");
        }

        Promocion promocion = new Promocion();
        promocion.setCodigo(dto.getCodigo().toUpperCase());
        promocion.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        promocion.setFechaExpiracion(dto.getFechaExpiracion());
        promocion.setActivo(true);

        return promocionRepository.save(promocion);
    }

    public Double validarCodigo(String codigo) {
        log.info("Validando código de descuento: {}", codigo);
        Promocion promocion = promocionRepository.findByCodigo(codigo.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Código de promoción no encontrado"));

        if (!promocion.getActivo()) {
            throw new RuntimeException("El código de promoción está inactivo");
        }

        if (promocion.getFechaExpiracion().isBefore(LocalDate.now())) {
            throw new RuntimeException("El código de promoción ha expirado");
        }

        log.info("Código válido. Descuento aplicable: {}%", promocion.getPorcentajeDescuento());
        return promocion.getPorcentajeDescuento();
    }
}