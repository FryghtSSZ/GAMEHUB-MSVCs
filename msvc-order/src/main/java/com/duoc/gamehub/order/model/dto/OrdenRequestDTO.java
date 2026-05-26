package com.duoc.gamehub.order.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrdenRequestDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotEmpty(message = "La orden debe contener al menos un detalle")
    @Valid
    private List<DetalleOrdenRequestDTO> detalles;
}