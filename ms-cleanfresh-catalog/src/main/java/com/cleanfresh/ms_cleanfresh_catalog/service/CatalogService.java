package com.cleanfresh.ms_cleanfresh_catalog.service;

import com.cleanfresh.ms_cleanfresh_catalog.dto.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    private final List<ServiceResponse> servicios = List.of(
            new ServiceResponse(1L, "Lavado y secado", "Lavado y secado estándar de ropa", 25.0, 2.0, true),
            new ServiceResponse(2L, "Lavado en seco", "Limpieza en seco para prendas delicadas", 45.0, 24.0, true),
            new ServiceResponse(3L, "Planchado", "Planchado de prendas", 15.0, 1.0, true),
            new ServiceResponse(4L, "Lavado de edredones", "Lavado especial para edredones y cobijas", 35.0, 4.0, false),
            new ServiceResponse(5L, "Servicio exprés", "Lavado y secado en menos de 1 hora", 40.0, 1.0, true)
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
