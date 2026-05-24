package com.duoc.gamehub.inventory.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservaStockDTO {
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad a reservar es obligatoria")
    @Min(value = 1, message = "Debe reservar al menos 1 unidad")
    private Integer cantidad;
}