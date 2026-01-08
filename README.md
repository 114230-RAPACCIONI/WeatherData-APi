[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/xgodUHCd)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=12195207)
# Weather Data API - Practica Parcial 1

API que consume diferentes fuentes de datos meteorológicos y los centraliza en una única respuesta por ubicación.

Como parte del requisito, debemos mantener la información de aquellos que consumen nuestra API, ya que al final de cada mes se genera un cargo por servicios de integración por otro sistema.

Adicionalmente, nuestro sistema retorna la información en un formato más amigable, dando significado a los datos que recupera del sistema meteorológico. Por ejemplo, además de retornar el Índice de Calidad del Aire (AQI), reportamos si el aire es seguro o no.

## Descripción

La API consume las siguientes fuentes de datos meteorológicos:
- [Locations](https://my-json-server.typicode.com/LCIV-2023/fake-weather/location)
- [Wind](https://my-json-server.typicode.com/LCIV-2023/fake-weather/wind)
- [Temperature](https://my-json-server.typicode.com/LCIV-2023/fake-weather/temperature)
- [Air Quality](https://my-json-server.typicode.com/LCIV-2023/fake-weather/air_quality)
- [Cloudiness](https://my-json-server.typicode.com/LCIV-2023/fake-weather/cloudiness)

### Características

- **Registro de clientes**: Los clientes deben registrarse con un email único y especificar su unidad de temperatura preferida (Celsius o Fahrenheit)
- **Autenticación**: Los clientes deben compartir su `client_id` y `secret` con la API para poder consumirla
- **Ubicaciones disponibles**: La API puede retornar información de todas las ubicaciones disponibles
- **Información meteorológica completa**: Para cada ubicación, la API retorna:
  - Nombre e ID de la ubicación
  - Temperatura en la unidad preferida del cliente
  - Velocidad del viento con dirección en formato amigable (ej: "10 km/h from North")
  - Índice de calidad del aire con descripción
  - Nubosidad expresada en oktas con descripción

**Nota sobre direcciones del viento:** La dirección del viento se recibe en grados considerando el norte como 0°. Un viento que sopla desde el norte tiene dirección 0° (360°); desde el este, 90°, etc.

## Requisitos Previos

Para ejecutar este proyecto necesitas tener instalado:

* **Java 17** o superior
* **Maven 3.6+** (o usar el wrapper incluido: `mvnw`)
* **Docker** y **Docker Compose** (para ejecutar con contenedores)
* **MySQL** (solo si ejecutas sin Docker)

## Cómo Levantar el Proyecto

### Opción 1: Usando Docker Compose (Recomendado)

Esta es la forma más sencilla de levantar el proyecto completo con todos sus servicios.

1. **Construir la aplicación:**
   ```bash
   ./mvnw clean install -DskipTests
   ```
   
   O en Windows:
   ```bash
   mvnw.cmd clean install -DskipTests
   ```

2. **Levantar los servicios con Docker Compose:**
   ```bash
   docker-compose up -d
   ```

3. **Verificar que los contenedores estén corriendo:**
   ```bash
   docker ps
   ```
   
   Deberías ver dos contenedores:
   - `weather_db` (MySQL) en el puerto 3306
   - `api_weather` (Spring Boot) en el puerto 8080

4. **Ver los logs de la aplicación:**
   ```bash
   docker logs -f api_weather
   ```

5. **Detener los servicios:**
   ```bash
   docker-compose down
   ```

   Para eliminar también los volúmenes de datos:
   ```bash
   docker-compose down -v
   ```

### Opción 2: Ejecución Local (Sin Docker)

Si prefieres ejecutar el proyecto localmente sin Docker:

1. **Configurar MySQL localmente:**
   - Instalar MySQL 8.0 o superior
   - Crear una base de datos llamada `java_db`
   - Usuario: `root`, Password: `root`

2. **Configurar el perfil de Spring:**
   - Usar el perfil por defecto (no `docker`)
   - El archivo `application.properties` está configurado para usar MySQL local

3. **Compilar y ejecutar:**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

   O en Windows:
   ```bash
   mvnw.cmd clean install
   mvnw.cmd spring-boot:run
   ```

## Cómo Usar la API

Una vez que la aplicación esté corriendo, estará disponible en: **http://localhost:8080**

### Documentación Interactiva (Swagger/OpenAPI)

Puedes acceder a la documentación interactiva de la API en:
- **Swagger UI**: http://localhost:8080/swagger-ui.html

### Endpoints Disponibles

#### 1. Registrar un nuevo suscriptor (cliente)

Registra un nuevo cliente en el sistema y obtén tus credenciales (`client_id` y `secret`).

**Endpoint:** `POST /weather/subscribe`

**Request:**
```bash
curl -X POST 'http://localhost:8080/weather/subscribe' \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@ejemplo.com",
    "temperature_unit": "C"
  }'
```

**Parámetros:**
- `email` (string, requerido): Email único del cliente
- `temperature_unit` (string, requerido): Unidad de temperatura preferida (`C` para Celsius o `F` para Fahrenheit)

**Response:**
```json
{
  "client_id": 1,
  "secret": "e58ed763-928c-4155-bee9-fdbaaadc15f3"
}
```

⚠️ **Importante:** Guarda estas credenciales, las necesitarás para acceder a los demás endpoints.

#### 2. Obtener todas las ubicaciones disponibles

Obtiene la lista de todas las ubicaciones disponibles en el sistema.

**Endpoint:** `GET /weather/locations`

**Request:**
```bash
curl -H "client_id: 1" \
     -H "client_secret: e58ed763-928c-4155-bee9-fdbaaadc15f3" \
     -X GET 'http://localhost:8080/weather/locations'
```

**Headers requeridos:**
- `client_id`: ID del cliente obtenido al registrarse
- `client_secret`: Secret del cliente obtenido al registrarse

**Response:**
```json
[
  {
    "id": 1,
    "name": "Location 1",
    "latitude": "40.7128",
    "longitude": "74.0060"
  },
  {
    "id": 2,
    "name": "Location 2",
    "latitude": "50.7128",
    "longitude": "84.0060"
  }
]
```

#### 3. Obtener información meteorológica de una ubicación específica

Obtiene información meteorológica completa para una ubicación en una fecha/hora específica.

**Endpoint:** `GET /weather/location/{location_id}`

**Request:**
```bash
curl -H "client_id: 1" \
     -H "client_secret: e58ed763-928c-4155-bee9-fdbaaadc15f3" \
     -X GET 'http://localhost:8080/weather/location/1?datetime=2017-01-01T00:01:00.000Z'
```

**Parámetros:**
- `location_id` (path parameter): ID de la ubicación
- `datetime` (query parameter, requerido): Fecha y hora en formato ISO 8601 (ej: `2017-01-01T00:01:00.000Z`)

**Headers requeridos:**
- `client_id`: ID del cliente
- `client_secret`: Secret del cliente

**Response:**
```json
[
  {
    "location": {
      "id": 1,
      "name": "Location 1"
    },
    "temperature": {
      "value": 20,
      "unit": "C"
    },
    "wind": "10 Km/h from North",
    "air_quality": {
      "index": 50,
      "description": "Good"
    },
    "cloudiness": {
      "index": 0,
      "description": "Clear sky"
    }
  }
]
```

### Tablas de Referencia

#### Índice de Calidad del Aire

| Índice | Descripción |
|--------|-------------|
| 0 - 50 | Good |
| 51 - 100 | Moderate |
| 101 - 150 | Unhealthy for Sensitive Groups |
| 151 - 200 | Unhealthy |
| 201 - 300 | Very Unhealthy |
| 301 - 500 | Hazardous |

#### Índice de Nubosidad (Oktas)

| Índice | Descripción |
|--------|-------------|
| 0 | Clear sky |
| 1 - 3 | Few clouds |
| 4 - 6 | Sky half cloudy |
| 7 - 8 | Sky completely cloudy |

**Nota:** Un okta es una unidad de medida que describe la cantidad de cobertura de nubes, donde 0 significa cielo completamente despejado y 8 significa completamente nublado.

## Solución de Problemas

### Error de conexión a MySQL

Si ves errores de conexión al inicio, es normal. Spring Boot intentará reconectarse automáticamente. Si el problema persiste:

1. Verifica que MySQL esté corriendo:
   ```bash
   docker logs weather_db
   ```

2. Verifica la conectividad entre contenedores:
   ```bash
   docker exec api_weather ping clients
   ```

### La aplicación no inicia

1. Verifica los logs:
   ```bash
   docker logs api_weather
   ```

2. Asegúrate de que el puerto 8080 no esté en uso:
   ```bash
   lsof -i :8080  # macOS/Linux
   netstat -ano | findstr :8080  # Windows
   ```

### Problemas de compilación con Maven

Si encuentras errores de codificación al compilar:

1. El proyecto está configurado para usar UTF-8
2. Asegúrate de tener Maven 3.6 o superior
3. Si el problema persiste, limpia el proyecto:
   ```bash
   ./mvnw clean
   ```

## Consideraciones Técnicas

* La aplicación está desarrollada con **Spring Boot 3.3.4** y **Java 17**
* Las pruebas se realizan con **JUnit 5** y **Mockito**
* La aplicación puede ejecutarse en un contenedor Docker
* La aplicación con la base de datos MySQL productiva puede ejecutarse con **Docker Compose**
* El proyecto usa **MySQL 9.5** como base de datos
* Documentación API disponible con **SpringDoc OpenAPI 3**

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/tomas/miproyecto/
│   │   ├── clients/          # Clientes REST para servicios externos
│   │   ├── config/           # Configuraciones (RestTemplate, Mappers, Swagger)
│   │   ├── controllers/      # Controladores REST
│   │   ├── dtos/             # Objetos de Transferencia de Datos
│   │   ├── entities/         # Entidades JPA
│   │   ├── repositories/     # Repositorios de datos
│   │   └── services/         # Lógica de negocio
│   └── resources/
│       ├── application.properties           # Configuración local
│       └── application-docker.properties    # Configuración Docker
└── test/                     # Pruebas unitarias e integración
```
