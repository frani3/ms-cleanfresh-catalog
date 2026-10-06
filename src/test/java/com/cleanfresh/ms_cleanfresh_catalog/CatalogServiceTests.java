package com.cleanfresh.ms_cleanfresh_catalog;

import com.cleanfresh.ms_cleanfresh_catalog.dto.ServiceResponse;
import com.cleanfresh.ms_cleanfresh_catalog.service.CatalogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CatalogServiceTests {

    @Autowired
    private CatalogService catalogService;

    @Test
    void siembraLosCincoServiciosDeEp1EnOrdenDeId() {
        List<ServiceResponse> servicios = catalogService.obtenerTodos();

        assertEquals(5, servicios.size());
        assertEquals("Lavado y secado", servicios.get(0).nombre());
        assertEquals(25000.0, servicios.get(0).precio());
    }

    @Test
    void conservaLaDisponibilidadPorSucursal() {
        ServiceResponse edredones = catalogService.obtenerPorId(4L).orElseThrow();

        assertFalse(edredones.disponible());
        assertEquals(4, edredones.sucursales().size());
        assertTrue(edredones.sucursales().get("Providencia"));
        assertFalse(edredones.sucursales().get("Las Condes"));
    }

    @Test
    void disponiblesExcluyeLosNoDisponibles() {
        List<ServiceResponse> disponibles = catalogService.obtenerDisponibles();

        assertEquals(4, disponibles.size());
        assertTrue(disponibles.stream().allMatch(ServiceResponse::disponible));
    }
}
