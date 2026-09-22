package com.cleanfresh.ms_cleanfresh_catalog.controller;

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
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<ServiceResponse> obtenerTodos() {
        return catalogService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> obtenerPorId(@PathVariable Long id) {
        return catalogService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibles")
    public List<ServiceResponse> obtenerDisponibles() {
        return catalogService.obtenerDisponibles();
    }
}
