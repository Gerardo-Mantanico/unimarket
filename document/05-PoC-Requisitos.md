# 5. Pruebas de Concepto - Requisitos (Sección 4.3.1)

## 5.1 Stack Tecnológico Seleccionado

| Componente | Tecnología | Versión | Justificación de la Selección |
|------------|------------|---------|------------------------------|
| **Lenguaje** | Java | 21 LTS | LTS actual, amplio soporte empresarial, excelente rendimiento en la nube |
| **Framework** | Spring Boot | 4.1.1 | Ecosistema maduro, soporte nativo para cloud-native, integración con todos los proveedores |
| **Base de datos** | PostgreSQL | 15 | Open source, disponible como servicio gestionado en los 4 proveedores |
| **Migraciones** | Flyway | (via Spring Boot) | Control de versiones de esquema, desplegable en cualquier entorno |
| **API Docs** | Springdoc OpenAPI | 2.8.11 | Documentación automática de endpoints, UI Swagger integrada |
| **Almacenamiento** | S3-compatible API | — | API estándar de la industria, compatible con todos los proveedores |
| **Construcción** | Maven | (wrapper) | Gestión de dependencias, build reproducible |

### ¿Por qué este stack?

- **Java 21**: Es el LTS actual con soporte hasta 2029+, mejoras de rendimiento significativas
- **Spring Boot**: Framework más utilizado para microservicios Java, excelente documentación
- **PostgreSQL**: Base de datos open source más popular, disponible en todos los proveedores
- **S3 API**: Estándar de facto para almacenamiento de objetos, compatible con todos los proveedores

## 5.2 Servicios Mínimos a Desplegar por Proveedor

En cada una de las cuatro plataformas se desplegará:

### 1. Servicio de Computación
- Instancia virtual ejecutando la API REST Spring Boot
- Endpoints implementados:
  - `GET /health` — Verificación de salud del servicio
  - `GET /api/products` — Listar productos
  - `GET /api/products/{id}` — Obtener producto por ID
  - `POST /api/products` — Crear producto (persistencia)
  - `PUT /api/products/{id}` — Actualizar producto
  - `DELETE /api/products/{id}` — Eliminar producto
  - `GET /api/categories` — Listar categorías

### 2. Servicio de Base de datos
- PostgreSQL gestionada en la nube
- Migraciones ejecutadas automáticamente con Flyway
- Tablas: `products`, `categories`

### 3. Servicio de Almacenamiento (opcional pero recomendado)
- Bucket/Space para imágenes de productos
- Integración con el SDK de cada proveedor

## 5.3 Evidencia de Funcionamiento

| Tipo de Evidencia | Descripción |
|-------------------|-------------|
| **Capturas de pantalla** | Consola del proveedor mostrando el servicio activo |
| **Logs de aplicación** | Salida de Spring Boot confirmando inicio exitoso |
| **Pruebas de endpoint** | Respuestas HTTP de `/health` y `/api/products` |
| **Pruebas de BD** | Confirmación de conexión a PostgreSQL y ejecución de queries |
| **URL de acceso** | Endpoint público accesible desde internet |

## 5.4 Documentación del Proceso

| Aspecto a Documentar | Métrica |
|---------------------|---------|
| **Tiempo invertido** | Horas y minutos desde inicio hasta despliegue funcional |
| **Dificultades encontradas** | Lista detallada de obstáculos y cómo se resolvieron |
| **Calidad de documentación** | Evaluación de las fuentes consultadas (1-5) |
| **Costo real incurrido** | Desglose de costos durante la prueba |
| **Herramientas utilizadas** | CLI, consola web, SDKs, editores |
| **Comandos ejecutados** | Registro de comandos importantes para reproducibilidad |

---

## Secciones Siguientes

- [6. Despliegue AWS](06-Despliegue-AWS.md)
- [7. Despliegue GCP](07-Despliegue-GCP.md)
- [8. Despliegue Azure](08-Despliegue-Azure.md)
- [9. Despliegue DigitalOcean](09-Despliegue-DigitalOcean.md)
- [Índice completo](README.md)
