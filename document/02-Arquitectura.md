# 2. Arquitectura de UniMarket CUNOC

## 2.1 Diagrama de Componentes

```
┌─────────────────────────────────────────────────────────────────┐
│                      UniMarket CUNOC                            │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐  │
│  │   Products   │  │  Documents   │  │   Storage Services   │  │
│  │   Module     │  │  Module      │  │   (S3/Azure/GCP/DO)  │  │
│  │              │  │              │  │                      │  │
│  │ - CRUD       │  │ - Upload     │  │ - Images             │  │
│  │ - Categories │  │ - Download   │  │ - Documents          │  │
│  │ - Search     │  │ - Delete     │  │ - Backups            │  │
│  └──────┬───────┘  └──────┬───────┘  └──────────┬───────────┘  │
│         │                 │                      │              │
│         └─────────────────┼──────────────────────┘              │
│                           │                                     │
│                   ┌───────▼───────┐                             │
│                   │  PostgreSQL   │                             │
│                   │  Database     │                             │
│                   │  (Managed)    │                             │
│                   └───────────────┘                             │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │              Flyway (Migraciones de Esquema)             │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │              Springdoc OpenAPI (Documentación API)       │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

## 2.2 Módulos del Sistema

| Módulo | Responsabilidad | Endpoints Principales |
|--------|-----------------|----------------------|
| **Products** | Gestión de productos y categorías | `GET/POST/PUT/DELETE /api/products`, `GET /api/categories` |
| **Documents** | Gestión de documentos | `POST /api/documents`, `GET /api/documents/{id}` |
| **Storage** | Almacenamiento de archivos en nube | Integración con S3, Azure Blob, GCP Storage |
| **Health** | Verificación de salud | `GET /health` |

## 2.3 Endpoints del API

| Endpoint | Método | Descripción | Request Body | Response |
|----------|--------|-------------|--------------|----------|
| `/health` | GET | Verificación de salud | — | `{"status": "UP"}` |
| `/api/products` | GET | Listar todos los productos | — | `List<ProductResponse>` |
| `/api/products/{id}` | GET | Obtener producto por ID | — | `ProductResponse` |
| `/api/products` | POST | Crear nuevo producto | `ProductCreateRequest` | `ProductResponse` |
| `/api/products/{id}` | PUT | Actualizar producto | `ProductRequest` | `ProductResponse` |
| `/api/products/{id}` | DELETE | Eliminar producto | — | `204 No Content` |
| `/api/categories` | GET | Listar categorías | — | `List<CategoryResponse>` |
| `/api/documents` | POST | Subir documento | `MultipartFile` | `DocumentResponse` |

## 2.4 Servicios de Infraestructura Requeridos

| Servicio | Propósito | Obligatorio |
|----------|-----------|-------------|
| **Computación** | Ejecutar la API REST Spring Boot | ✅ Sí |
| **Base de datos** | Persistencia PostgreSQL gestionada | ✅ Sí |
| **Almacenamiento** | Imágenes de productos y documentos | ✅ Sí |
| **Red** | Conectividad y aislamiento | ✅ Sí |
| **Balanceo de carga** | Distribución de tráfico | Opcional |
| **CDN** | Cache de assets estáticos | Opcional |
| **Monitoreo** | Observabilidad del sistema | Opcional |

---

## Secciones Siguientes

- [3. Mapeo de Servicios Equivalentes](03-Mapeo-Servicios.md)
- [Índice completo](README.md)
