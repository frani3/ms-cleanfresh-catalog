package com.cleanfresh.ms_cleanfresh_catalog.dto;

public record ServiceResponse(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        Double duracionHoras,
        Boolean disponible
) {
}
