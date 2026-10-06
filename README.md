# ms-cleanfresh-catalog

Microservicio de catálogo de servicios de Clean&Fresh Manager. Spring
Boot 4.1.1 / Java 21. Guarda el catálogo en PostgreSQL (base
`catalog_db`) con Spring Data JPA y se consume únicamente a través del BFF
(`ms-cleanfresh-bff`) — no valida JWT por su cuenta, confía en que solo
el BFF le habla.

Proyecto individual de **DSY1107 Cloud Native 1** (DuocUC). Desde EP2 los
datos viven en PostgreSQL (en EP1 eran listas en memoria).

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/catalog` | Todos los servicios |
| GET | `/api/catalog/{id}` | Un servicio por id |
| GET | `/api/catalog/disponibles` | Servicios disponibles en alguna sucursal |

Cada servicio incluye `sucursales`, un mapa de disponibilidad por
sucursal (Providencia, Ñuñoa, Las Condes, Maipú), usado por el BFF para
filtrar el catálogo que ve cada Operador/Cliente según sucursal. En la base,
ese mapa vive en la tabla `servicio_sucursal` (una fila por servicio y
sucursal). Si la tabla de servicios está vacía, al arrancar se cargan los 5
servicios de ejemplo de EP1.

## Requisitos

- Java 21 (`JAVA_HOME` apuntando a un JDK 21)
- Una base PostgreSQL con `catalog_db` y un usuario con acceso solo a ella

## Configuración (variables de entorno)

La conexión llega solo por variables de entorno, sin valores por defecto: si
faltan, el servicio no arranca.

| Variable | Ejemplo |
|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/catalog_db` |
| `DB_USER` | `catalog_user` |
| `DB_PASSWORD` | (la del usuario) |

Las tablas se crean/actualizan solas (`ddl-auto: update`).

## Levantar en local

Base de datos de prueba con Docker (credenciales de ejemplo, cámbialas):

```powershell
docker run -d --name cleanfresh-pg -e POSTGRES_PASSWORD=<admin> -p 5432:5432 postgres:16
docker exec -it cleanfresh-pg psql -U postgres -c "CREATE USER catalog_user WITH PASSWORD '<clave>'" -c "CREATE DATABASE catalog_db OWNER catalog_user"
```

Luego, con las tres variables definidas:

```powershell
.\mvnw.cmd spring-boot:run
```

O compilar y correr el jar:

```powershell
.\mvnw.cmd clean package -DskipTests
java -jar target\ms-cleanfresh-catalog-0.0.1-SNAPSHOT.jar
```

Los tests (`.\mvnw.cmd test`) usan H2 en memoria y no necesitan PostgreSQL.

Corre en `http://localhost:8082`.

## Probar

```bash
curl http://localhost:8082/api/catalog
```

## Arquitectura y decisiones técnicas

Ver [`CLAUDE.md`](CLAUDE.md) para el detalle completo del sistema (los
4 repos, cómo se conecta con el BFF, y la pauta de evaluación de EP1).

## Docker

`Dockerfile` multi-etapa (Maven + JDK 21 para compilar, JRE 21 sin root para correr). Se configura solo por variables de entorno.

```bash
docker build -t cleanfresh/catalog .
docker run -p 8082:8082 -e DB_URL=... -e DB_USER=... -e DB_PASSWORD=... cleanfresh/catalog
```

Los 5 microservicios se levantan juntos con el `docker-compose.yml` de `EP2/despliegue/` en el repo `cleanfresh-frontend`.
