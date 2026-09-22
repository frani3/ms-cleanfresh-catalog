package com.cleanfresh.ms_cleanfresh_catalog.dto;

import java.util.Map;

public record ServiceResponse(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        Double duracionHoras,
        Boolean disponible,
        Map<String, Boolean> sucursales
) {
}
