package com.cleanfresh.ms_cleanfresh_catalog.config;

import com.cleanfresh.ms_cleanfresh_catalog.entity.ServiceEntity;
import com.cleanfresh.ms_cleanfresh_catalog.repository.ServiceJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Con la base vacía, carga los mismos 5 servicios que tenía el catálogo en
 * memoria (EP1), para que las respuestas no cambien. Si ya hay datos, no toca nada.
 */
@Component
public class CatalogDataInitializer implements CommandLineRunner {

    // Todas las sucursales habilitadas, salvo que se indique lo contrario.
    private static final Map<String, Boolean> TODAS = Map.of(
            "Providencia", true,
            "Ñuñoa", true,
            "Las Condes", true,
            "Maipú", true
    );

    private final ServiceJpaRepository repository;

    public CatalogDataInitializer(ServiceJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }
        repository.saveAll(List.of(
                new ServiceEntity("Lavado y secado", "Lavado y secado estándar de ropa",
                        25000.0, 2.0, true, TODAS),
                new ServiceEntity("Lavado en seco", "Limpieza en seco para prendas delicadas",
                        45000.0, 24.0, true, TODAS),
                new ServiceEntity("Planchado", "Planchado de prendas",
                        15000.0, 1.0, true, TODAS),
                new ServiceEntity("Lavado de edredones", "Lavado especial para edredones y cobijas",
                        35000.0, 4.0, false,
                        Map.of("Providencia", true, "Ñuñoa", true, "Las Condes", false, "Maipú", false)),
                new ServiceEntity("Servicio exprés", "Lavado y secado en menos de 1 hora",
                        40000.0, 1.0, true,
                        Map.of("Providencia", true, "Ñuñoa", true, "Las Condes", true, "Maipú", false))
        ));
    }
}
