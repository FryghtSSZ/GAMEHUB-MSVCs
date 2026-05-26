package com.duoc.gamehub.promotion.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PromocionRequestDTO {

    @NotBlank(message = "El código de la promoción es obligatorio")
    private String codigo;

    @NotNull(message = "El porcentaje es obligatorio")
    @Min(value = 1, message = "El descuento mínimo es 1%")
    @Max(value = 100, message = "El descuento máximo es 100%")
    private Double porcentajeDescuento;

    @NotNull(message = "La fecha de expiración es obligatoria")
    private LocalDate fechaExpiracion;
}