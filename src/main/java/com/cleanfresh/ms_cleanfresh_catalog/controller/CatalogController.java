package com.cleanfresh.ms_cleanfresh_catalog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.cleanfresh.ms_cleanfresh_catalog.dto.ServiceResponse;
import com.cleanfresh.ms_cleanfresh_catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
@Tag(name = "Catálogo", description = "Servicios de lavandería y su disponibilidad por sucursal. Solo lectura. Solo lo consulta el BFF.")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Operation(summary = "Lista todos los servicios del catálogo")
    @GetMapping
    public List<ServiceResponse> obtenerTodos() {
        return catalogService.obtenerTodos();
    }

    @Operation(summary = "Obtiene un servicio por su id (404 si no existe)")
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> obtenerPorId(@PathVariable Long id) {
        return catalogService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lista los servicios disponibles al menos en una sucursal")
    @GetMapping("/disponibles")
    public List<ServiceResponse> obtenerDisponibles() {
        return catalogService.obtenerDisponibles();
    }
}
