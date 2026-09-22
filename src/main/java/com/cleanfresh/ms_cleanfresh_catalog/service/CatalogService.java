package com.cleanfresh.ms_cleanfresh_catalog.service;

import com.cleanfresh.ms_cleanfresh_catalog.dto.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CatalogService {

    // Todas las sucursales habilitadas, salvo que se indique lo contrario.
    private static final Map<String, Boolean> TODAS_LAS_SUCURSALES = Map.of(
            "Providencia", true,
            "Ñuñoa", true,
            "Las Condes", true,
            "Maipú", true
    );

    private final List<ServiceResponse> servicios = List.of(
            new ServiceResponse(1L, "Lavado y secado", "Lavado y secado estándar de ropa", 25000.0, 2.0, true,
                    TODAS_LAS_SUCURSALES),
            new ServiceResponse(2L, "Lavado en seco", "Limpieza en seco para prendas delicadas", 45000.0, 24.0, true,
                    TODAS_LAS_SUCURSALES),
            new ServiceResponse(3L, "Planchado", "Planchado de prendas", 15000.0, 1.0, true,
                    TODAS_LAS_SUCURSALES),
            new ServiceResponse(4L, "Lavado de edredones", "Lavado especial para edredones y cobijas", 35000.0, 4.0, false,
                    Map.of("Providencia", true, "Ñuñoa", true, "Las Condes", false, "Maipú", false)),
            new ServiceResponse(5L, "Servicio exprés", "Lavado y secado en menos de 1 hora", 40000.0, 1.0, true,
                    Map.of("Providencia", true, "Ñuñoa", true, "Las Condes", true, "Maipú", false))
    );

    public List<ServiceResponse> obtenerTodos() {
        return servicios;
    }

    public Optional<ServiceResponse> obtenerPorId(Long id) {
        return servicios.stream()
                .filter(servicio -> servicio.id().equals(id))
                .findFirst();
    }

    public List<ServiceResponse> obtenerDisponibles() {
        return servicios.stream()
                .filter(ServiceResponse::disponible)
                .toList();
    }
}
