# 11. Recomendación Fundamentada (Sección 4.4)

## 11.1 Recomendación Técnica

**Proveedor recomendado: Google Cloud Platform (GCP)**

### Justificación de la Recomendación

| Criterio | Evaluación | Peso | GCP | AWS | Azure | DO |
|----------|------------|------|-----|-----|-------|-----|
| **Precio** | Alto | 30% | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐ | ⭐⭐⭐⭐ |
| **Simplicidad** | Alto | 25% | ⭐⭐⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| **Servicios Managed** | Medio | 20% | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Documentación** | Medio | 15% | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| **Ecosistema** | Bajo | 10% | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐ |
| **Ponderado** | | 100% | **4.25** | **3.55** | **2.90** | **3.70** |

**GCP es el mejor balance entre:**
1. **Costo**: El más económico para el caso de estudio (~$19.93/mes)
2. **Simplicidad**: Consola moderna, CLI intuitiva, buena documentación
3. **Servicios**: Cloud SQL excelente, Cloud Storage confiable, Compute Engine flexible
4. **Free tier**: $300 créditos por 90 días — ideal para pruebas y desarrollo inicial

## 11.2 Ventajas y Desventajas de Cada Proveedor

| Proveedor | Ventajas | Desventajas |
|-----------|----------|-------------|
| **AWS** | Catálogo más amplio, mayor madurez, más regiones, mayor ecosistema | Curva de aprendizaje pronunciada, consola sobrecargada, costos más altos |
| **GCP** | Mejor precio, consola moderna, $300 créditos, BigQuery/Vertex AI, Cloud Run | Menor cuota de mercado, menos regiones en LATAM, comunidad más pequeña |
| **Azure** | Integración con Microsoft, Azure DevOps, buena opción para .NET | Significativamente más caro, configuración compleja para Java |
| **DigitalOcean** | Simplicidad extrema, documentación excelente, precios predecibles | Ecosistema limitado, sin serverless, menos servicios managed |

## 11.3 Arquitectura de Despliegue Conceptual en GCP

```
┌─────────────────────────────────────────────────────────────────────┐
│                    GCP - UniMarket CUNOC (Producción)              │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  ┌───────────────────────────────────────────────────────────────┐  │
│  │                    Cloud DNS                                  │  │
│  │              unimarketcunoc.com → IP Load Balancer            │  │
│  └───────────────────────────────────────────────────────────────┘  │
│                              │                                      │
│                              ▼                                      │
│  ┌───────────────────────────────────────────────────────────────┐  │
│  │                  Cloud Load Balancer                          │  │
│  │            (HTTPS con certificado SSL automático)             │  │
│  └───────────────────────────────────────────────────────────────┘  │
│                              │                                      │
│                              ▼                                      │
│  ┌───────────────────────────────────────────────────────────────┐  │
│  │                    Cloud CDN                                  │  │
│  │              (Cache de assets estáticos)                      │  │
│  └───────────────────────────────────────────────────────────────┘  │
│                              │                                      │
│                              ▼                                      │
│  ┌───────────────────────────────────────────────────────────────┐  │
│  │              Compute Engine (e2-small)                        │  │
│  │                                                               │  │
│  │  ┌─────────────────────────────────────────────────────────┐  │  │
│  │  │        Spring Boot API (Java 21)                        │  │  │
│  │  │                                                         │  │  │
│  │  │  - GET  /health                                         │  │  │
│  │  │  - GET  /api/products                                   │  │  │
│  │  │  - POST /api/products                                   │  │  │
│  │  │  - PUT  /api/products/{id}                              │  │  │
│  │  │  - DELETE /api/products/{id}                            │  │  │
│  │  │  - GET  /api/categories                                 │  │  │
│  │  │  - POST /api/documents                                  │  │  │
│  │  └─────────────────────────────────────────────────────────┘  │  │
│  │                                                               │  │
│  │  ┌─────────────────────────────────────────────────────────┐  │  │
│  │  │        Springdoc OpenAPI (Swagger UI)                   │  │  │
│  │  │        http://<ip>:8080/swagger-ui.html                 │  │  │
│  │  └─────────────────────────────────────────────────────────┘  │  │
│  └───────────────────────────────────────────────────────────────┘  │
│                              │                                      │
│              ┌───────────────┼───────────────┐                      │
│              ▼                               ▼                      │
│  ┌───────────────────────┐     ┌───────────────────────┐            │
│  │      Cloud SQL        │     │     Cloud Storage     │            │
│  │   PostgreSQL 15       │     │   unimarket-images    │            │
│  │   db-f1-micro         │     │   Standard Class      │            │
│  │   10GB SSD            │     │                       │            │
│  └───────────────────────┘     └───────────────────────┘            │
│                                                                     │
│  ┌───────────────────────────────────────────────────────────────┐  │
│  │           Cloud Monitoring + Cloud Logging                    │  │
│  └───────────────────────────────────────────────────────────────┘  │
│                                                                     │
│  ┌───────────────────────────────────────────────────────────────┐  │
│  │                    VPC + Firewall Rules                        │  │
│  └───────────────────────────────────────────────────────────────┘  │
│                                                                     │
│  ┌───────────────────────────────────────────────────────────────┐  │
│  │              Secret Manager                                   │  │
│  └───────────────────────────────────────────────────────────────┘  │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

### Servicios Específicos en GCP

| Componente UniMarket | Servicio GCP | Configuración Recomendada | Costo Mensual |
|---------------------|--------------|---------------------------|---------------|
| **API REST** | Compute Engine | e2-small, Ubuntu 22.04, Java 21 | ~$12.16 |
| **Base de datos** | Cloud SQL | PostgreSQL 15, db-f1-micro, 10GB SSD | ~$7.67 |
| **Imágenes** | Cloud Storage | Standard bucket, us-central1 | ~$0.10 |
| **Balanceo carga** | Cloud Load Balancing | HTTP(S) Load Balancer | ~$5.00 |
| **CDN** | Cloud CDN | Cache de assets estáticos | ~$1.00 |
| **DNS** | Cloud DNS | Zona DNS gestionada | ~$0.20 |
| **Monitoreo** | Cloud Monitoring | Métricas básicas | Gratuito |
| **Logs** | Cloud Logging | Logs de aplicación | Gratuito |
| **Firewall** | VPC Firewall Rules | Reglas de acceso | Gratuito |
| **Secrets** | Secret Manager | Credenciales y passwords | ~$0.06 |
| **TOTAL** | | | **~$26.19/mes** |

---

## Secciones Siguientes

- [12. Reflexión Crítica](12-Reflexion-Critica.md)
- [Índice completo](README.md)
