# ms-cleanfresh-catalog

Microservicio de catálogo de servicios de Clean&Fresh Manager. Spring
Boot 4.1.1 / Java 21. Sirve datos mock en memoria (sin base de datos
todavía) y se consume únicamente a través del BFF
(`ms-cleanfresh-bff`) — no valida JWT por su cuenta, confía en que solo
el BFF le habla.

Proyecto individual para **EP1** de **DSY1107 Cloud Native 1** (DuocUC).

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/catalog` | Todos los servicios |
| GET | `/api/catalog/{id}` | Un servicio por id |
| GET | `/api/catalog/disponibles` | Servicios disponibles en alguna sucursal |

Cada servicio incluye `sucursales`, un mapa de disponibilidad por
sucursal (Providencia, Ñuñoa, Las Condes, Maipú), usado por el BFF para
filtrar el catálogo que ve cada Operador/Cliente según sucursal.

## Requisitos

- Java 21 (`JAVA_HOME` apuntando a un JDK 21)

## Levantar en local

```powershell
.\mvnw.cmd spring-boot:run
```

O compilar y correr el jar:

```powershell
.\mvnw.cmd clean package -DskipTests
java -jar target\ms-cleanfresh-catalog-0.0.1-SNAPSHOT.jar
```

Corre en `http://localhost:8082`.

## Probar

```bash
curl http://localhost:8082/api/catalog
```

## Arquitectura y decisiones técnicas

Ver [`CLAUDE.md`](CLAUDE.md) para el detalle completo del sistema (los
4 repos, cómo se conecta con el BFF, y la pauta de evaluación de EP1).
