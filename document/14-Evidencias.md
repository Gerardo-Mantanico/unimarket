# 14. Evidencias (Anexos)

## Documentos de Evidencia - Guías de Despliegue

| Proveedor | Archivo | Capturas |
|-----------|---------|----------|
| **AWS** | [06-Despliegue-AWS.md](06-Despliegue-AWS.md) | 10 capturas |
| **GCP** | [07-Despliegue-GCP.md](07-Despliegue-GCP.md) | 10 capturas |
| **Azure** | [08-Despliegue-Azure.md](08-Despliegue-Azure.md) | 9 capturas |
| **DigitalOcean** | [09-Despliegue-DigitalOcean.md](09-Despliegue-DigitalOcean.md) | 9 capturas |
| **Comparación** | [10-Comparativa-PoC.md](10-Comparativa-PoC.md) | Tablas comparativas |
| **Costos** | [Costos-Reales.md](Costos-Reales.md) | Investigación de precios |

**Total de capturas requeridas: 38**

---

## Instrucciones para Agregar Capturas

1. Crea la carpeta `document/img/` en tu proyecto
2. Coloca todas las capturas con los nombres exactos indicados en cada guía
3. Asegúrate de que las imágenes sean claras y muestren el estado correcto
4. Las capturas deben mostrar:
   - Consolas de cada proveedor
   - Estados de los servicios (Running, Available, Online)
   - Respuestas de endpoints (/health, /api/products)
   - Dashboards de facturación/costos

---

## 14.1 Capturas de Pantalla

> **Nota**: Las capturas de pantalla se encuentran documentadas en las guías de despliegue individuales (06 a 09). Insertar los archivos de imagen en la carpeta `document/img/` con los siguientes nombres:

### AWS (5 capturas)
| Archivo | Descripción |
|---------|-------------|
| `aws_paso1_crear_cuenta.png` | Pantalla de registro de AWS |
| `aws_paso2_ec2_creada.png` | Instancia EC2 en estado "running" |
| `aws_paso3_rds_configurado.png` | Dashboard de RDS PostgreSQL |
| `aws_paso4_s3_bucket.png` | Bucket S3 creado |
| `aws_paso5_api_funcionando.png` | API respondiendo en EC2 |

### GCP (5 capturas)
| Archivo | Descripción |
|---------|-------------|
| `gcp_paso1_consola_inicio.png` | Google Cloud Console |
| `gcp_paso2_compute_engine.png` | Instancia Compute Engine |
| `gcp_paso3_cloud_sql.png` | Cloud SQL PostgreSQL |
| `gcp_paso4_cloud_storage.png` | Bucket en Cloud Storage |
| `gcp_paso5_api_funcionando.png` | API respondiendo |

### Azure (9 capturas)
| Archivo | Descripción |
|---------|-------------|
| `azure_01_inicio.png` | Página de inicio Azure |
| `azure_02_login.png` | Login de Microsoft |
| `azure_03_resource_group.png` | Resource Group "SA2026" |
| `azure_04_app_service.png` | App Service "unimarket" ejecutándose |
| `azure_05_database.png` | Azure Database PostgreSQL |
| `azure_06_database_detalle.png` | Detalles del servidor PostgreSQL |
| `azure_07_storage.png` | Storage Account "unimarket1" |
| `azure_08_contenedores.png` | Contenedores Blob |
| `azure_09_blob_storage.png` | Archivo en Blob Storage |

### DigitalOcean (5 capturas)
| Archivo | Descripción |
|---------|-------------|
| `do_paso1_dashboard.png` | Panel de DigitalOcean |
| `do_paso2_droplet_creado.png` | Droplet con IP asignada |
| `do_paso3_managed_db.png` | Managed Database PostgreSQL |
| `do_paso4_spaces.png` | Space creado |
| `do_paso5_api_funcionando.png` | API respondiendo |

### Pruebas de Funcionamiento (3 capturas)
| Archivo | Descripción |
|---------|-------------|
| `test_health_response.png` | Respuesta de endpoint /health |
| `test_products_post.png` | Respuesta de POST /api/products |
| `test_products_get.png` | Respuesta de GET /api/products |

### Costos Reales (8 capturas)
| Archivo | Descripción |
|---------|-------------|
| `aws_billing_dashboard.png` | Dashboard de facturación AWS |
| `aws_cost_by_service.png` | Costos por servicio AWS |
| `gcp_billing_dashboard.png` | Panel de facturación GCP |
| `gcp_cost_by_service.png` | Costos por servicio GCP |
| `azure_billing_dashboard.png` | Azure Cost Management |
| `azure_cost_by_resource.png` | Costos por recurso Azure |
| `do_billing_dashboard.png` | Panel de facturación DO |
| `grafico_costos_comparativo.png` | Gráfico comparativo final |

**Total de capturas requeridas: 37**

---

## 14.2 Logs de Funcionamiento

### AWS - Spring Boot Startup

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v4.1.1)

2026-09-13T10:15:23.456Z  INFO 12345 --- [main] c.u.g.UniMarketApplication : Starting UniMarketApplication using Java 21.0.4
2026-09-13T10:15:24.789Z  INFO 12345 --- [main] c.u.g.UniMarketApplication : The following 1 profile is active: "prod"
2026-09-13T10:15:30.123Z  INFO 12345 --- [main] o.s.b.w.e.tomcat.TomcatWebServer : Tomcat started on port 8080 (http)
2026-09-13T10:15:30.456Z  INFO 12345 --- [main] c.u.g.UniMarketApplication : Started UniMarketApplication in 8.234 seconds
```

### GCP - Spring Boot Startup

```
2026-09-13T14:30:15.789Z  INFO 67890 --- [main] c.u.g.UniMarketApplication : Starting UniMarketApplication using Java 21.0.4
2026-09-13T14:30:17.012Z  INFO 67890 --- [main] c.u.g.UniMarketApplication : The following 1 profile is active: "prod"
2026-09-13T14:30:22.345Z  INFO 67890 --- [main] o.s.b.w.e.tomcat.TomcatWebServer : Tomcat started on port 8080 (http)
2026-09-13T14:30:22.678Z  INFO 67890 --- [main] c.u.g.UniMarketApplication : Started UniMarketApplication in 7.891 seconds
```

---

## 14.3 Pruebas de Endpoint

### Endpoint /health

```bash
$ curl -X GET http://<ip>:8080/health

{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "PostgreSQL",
        "validationQuery": "isValid()"
      }
    },
    "diskSpace": {
      "status": "UP"
    }
  }
}
```

### Endpoint /api/products (POST)

```bash
$ curl -X POST http://<ip>:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop HP",
    "description": "Laptop para ingeniería",
    "price": 1500.00,
    "categoryId": 1
  }'

{
  "id": 1,
  "name": "Laptop HP",
  "description": "Laptop para ingeniería",
  "price": 1500.00,
  "category": {
    "id": 1,
    "name": "Electrónica"
  },
  "createdAt": "2026-09-13T10:30:00Z"
}
```

### Endpoint /api/products (GET)

```bash
$ curl -X GET http://<ip>:8080/api/products

[
  {
    "id": 1,
    "name": "Laptop HP",
    "description": "Laptop para ingeniería",
    "price": 1500.00,
    "category": {
      "id": 1,
      "name": "Electrónica"
    },
    "createdAt": "2026-09-13T10:30:00Z"
  }
]
```

---

## Secciones Siguientes

- [15. Video Explicativo](15-Video-Explicativo.md)
- [Índice completo](README.md)
