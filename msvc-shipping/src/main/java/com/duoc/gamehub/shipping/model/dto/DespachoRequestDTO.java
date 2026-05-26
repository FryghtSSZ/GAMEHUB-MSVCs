package com.duoc.gamehub.shipping.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DespachoRequestDTO {

    @NotNull(message = "El ID de la orden es obligatorio")
    private Long ordenId;

    @NotNull(message = "El ID del usuario es obligatorio para validar")
    private Long usuarioId;

    @NotBlank(message = "La dirección de destino es obligatoria")
    private String direccionDestino;
}