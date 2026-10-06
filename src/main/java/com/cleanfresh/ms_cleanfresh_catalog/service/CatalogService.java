package com.cleanfresh.ms_cleanfresh_catalog.service;

import com.cleanfresh.ms_cleanfresh_catalog.dto.ServiceResponse;
import com.cleanfresh.ms_cleanfresh_catalog.entity.ServiceEntity;
import com.cleanfresh.ms_cleanfresh_catalog.repository.ServiceJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CatalogService {

    private final ServiceJpaRepository repository;

    public CatalogService(ServiceJpaRepository repository) {
        this.repository = repository;
    }

    public List<ServiceResponse> obtenerTodos() {
        return repository.findAllByOrderByIdAsc().stream().map(this::toResponse).toList();
    }

    public Optional<ServiceResponse> obtenerPorId(Long id) {
        return repository.findById(id).map(this::toResponse);
    }

    public List<ServiceResponse> obtenerDisponibles() {
        return repository.findByDisponibleTrueOrderByIdAsc().stream().map(this::toResponse).toList();
    }

    private ServiceResponse toResponse(ServiceEntity servicio) {
        return new ServiceResponse(
                servicio.getId(),
                servicio.getNombre(),
                servicio.getDescripcion(),
                servicio.getPrecio(),
                servicio.getDuracionHoras(),
                servicio.getDisponible(),
                Map.copyOf(servicio.getSucursales())
        );
    }
}
