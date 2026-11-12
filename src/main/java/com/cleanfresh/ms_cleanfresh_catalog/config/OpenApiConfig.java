package com.cleanfresh.ms_cleanfresh_catalog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Documentación OpenAPI del servicio: {@code /swagger-ui.html} y {@code /v3/api-docs}. */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(new Info()
                .title("ms-cleanfresh-catalog")
                .version("0.0.1")
                .description("Catálogo de servicios de lavandería de Clean&Fresh Manager y su disponibilidad por sucursal (PostgreSQL). "
                        + "Solo lectura. No es público: solo lo consulta el BFF."));
    }
}
