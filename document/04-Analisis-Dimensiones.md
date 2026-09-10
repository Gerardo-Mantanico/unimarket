# 4. Análisis por Dimensiones Técnicas (Sección 4.2)

## 4.1 Precio y Modelo de Costos (4.2.1)

### 4.1.1 Modelo de Precios por Proveedor

| Proveedor | Modelo de Precios | Free Tier | Facturación |
|-----------|-------------------|-----------|-------------|
| **AWS** | Pay-as-you-go, descuentos por uso reserved | 12 meses (750h EC2, 750h RDS, 5GB S3) | Por segundo (EC2), por hora (RDS) |
| **GCP** | Pay-as-you-go, descuentos sostenidos (automáticos) | $300 créditos por 90 días | Por segundo |
| **Azure** | Pay-as-you-go, descuentos por uso reserved | 12 meses + $200 créditos por 30 días | Por minuto |
| **DigitalOcean** | Precios fijos por plan, sin costos ocultos | $200 créditos por 60 días | Mensual fijo |

### 4.1.2 Estimación de Costos Mensuales

**Configuración de entrada solicitada:**
- 2 instancias de computación pequeñas
- 1 base de datos gestionada
- 1 bucket de almacenamiento (5GB)
- 1 millón de ejecuciones serverless

| Componente | AWS | GCP | Azure | DigitalOcean |
|------------|-----|-----|-------|--------------|
| **2x Computación** | 2x t3.small: $30.37 | 2x e2-small: $33.56 | 2x B1 App Service: $109.50 | 2x Droplet 2GB: $36.00 |
| **1x Base de datos** | db.t3.micro: $12.41 | db-f1-micro: $7.67 | B1ms PostgreSQL: $49.15 | 1GB Managed DB: $15.00 |
| **1x Almacenamiento (5GB)** | S3 Standard: $0.115 | Cloud Storage: $0.10 | Blob Storage: $0.08 | Spaces: $5.00 (250GB) |
| **1M Ejecuciones Serverless** | Lambda: $0.20 | Cloud Functions: $0.40 | Azure Functions: $0.20 | — (no disponible) |
| **Transferencia (10GB saliente)** | $0.90 | $1.20 | $0.87 | $0.10 |
| **TOTAL MENSUAL** | **~$44.00** | **~$42.93** | **~$159.80** | **~$56.10** |

> **Nota**: Los costos son estimaciones basadas en precios públicos al 13 de septiembre de 2026.

### 4.1.3 Costos Ocultos y Factores Indirectos

| Factor | AWS | GCP | Azure | DigitalOcean |
|--------|-----|-----|-------|--------------|
| **Egress (datos salientes)** | $0.09/GB (primeros 10TB) | $0.12/GB (primeros 1TB) | $0.087/GB (primeros 5GB) | $0.01/GB (más barato) |
| **Backups de BD** | $0.095/GB/mes | $0.08/GB/mes | $0.095/GB/mes | Incluido |
| **Snapshots** | $0.05/GB/mes | $0.026/GB/mes | $0.05/GB/mes | $0.06/GB/mes |
| **Soporte técnico** | Desde $29/mes (Basic) | Desde $29/mes (Silver) | Desde $29/mes (Developer) | Incluido (Community) |
| **IP pública estática** | $3.65/mes | $0.01/hora (~$7.30) | Incluida | Incluida |
| **Logs y monitoreo** | CloudWatch: $0.30/mes por métrica | Cloud Monitoring: Básico gratis | Azure Monitor: Básico gratis | Monitoring: Incluido |

### 4.1.4 Fuentes y Fechas de Consulta

| Proveedor | Fuente | URL | Fecha de Consulta |
|-----------|--------|-----|-------------------|
| AWS | AWS Pricing Calculator | https://calculator.aws/ | 2026-09-13 |
| GCP | GCP Pricing Calculator | https://cloud.google.com/products/calculator | 2026-09-13 |
| Azure | Azure Pricing Calculator | https://azure.microsoft.com/pricing/calculator/ | 2026-09-13 |
| DigitalOcean | DigitalOcean Pricing | https://www.digitalocean.com/pricing | 2026-09-13 |

---

## 4.2 Seguridad (4.2.2)

### 4.2.1 Capacidades de Seguridad por Proveedor

| Capacidad | AWS | GCP | Azure | DigitalOcean |
|------------|-----|-----|-------|--------------|
| **Cifrado en tránsito** | TLS 1.2+ (forzado por defecto) | TLS 1.2+ (configurable) | TLS 1.2+ (forzado) | TLS 1.2+ |
| **Cifrado en reposo** | S3 SSE, RDS Encryption, EBS Encryption | Cloud SQL Encryption, CSEK, Cloud Storage Encryption | Azure SQL TDE, Blob Encryption, Disk Encryption | Managed DB Encryption, Spaces Encryption |
| **IAM** | IAM completo: usuarios, grupos, roles, policies, MFA | IAM con principals, service accounts, Workload Identity | Azure AD, RBAC, Managed Identity | IAM básico: roles limitados |
| **Compliance** | SOC 1/2/3, ISO 27001, HIPAA, PCI-DSS, FedRAMP | SOC 1/2/3, ISO 27001, HIPAA, PCI-DSS | SOC 1/2/3, ISO 27001, HIPAA, PCI-DSS, GDPR | SOC 2, ISO 27001 |
| **Firewalls** | Security Groups, NACLs, AWS WAF | VPC Firewall Rules, Cloud Armor | NSG, Azure Firewall, WAF | Cloud Firewalls (plan-based) |
| **Detección amenazas** | GuardDuty, Inspector, Macie | Security Command Center, Chronicle | Microsoft Defender for Cloud, Sentinel | — (limitado) |
| **Gestión de secretos** | Secrets Manager, Parameter Store | Secret Manager | Key Vault | Environment Variables |

### 4.2.2 Modelo de Responsabilidad Compartida

| Aspecto | Responsabilidad del Proveedor | Responsabilidad del Cliente |
|---------|------------------------------|----------------------------|
| **Infraestructura física** | ✅ Proveedor | ❌ Cliente |
| **Hipervisor/OS** | ✅ Proveedor (IaaS) / ❌ Cliente | ❌ Proveedor (PaaS) / ✅ Cliente |
| **Datos** | ❌ Proveedor | ✅ Cliente |
| **Aplicación** | ❌ Proveedor | ✅ Cliente |
| **Identidad y acceso** | ✅ Proveedor (herramientas) | ✅ Cliente (configuración) |
| **Red** | ✅ Proveedor (infraestructura) | ✅ Cliente (configuración) |

**Documentación por proveedor:**
- AWS: [Shared Responsibility Model](https://aws.amazon.com/compliance/shared-responsibility-model/)
- GCP: [Shared Responsibility Model](https://cloud.google.com/docs/shared-responsibility-model)
- Azure: [Shared Responsibility Model](https://learn.microsoft.com/en-us/azure/security/fundamentals/shared-responsibility)
- DigitalOcean: [Cloud Security](https://www.digitalocean.com/docs/security/overview)

---

## 4.3 Velocidad y Rendimiento (4.2.3)

### 4.3.1 Latencia de Red y Regiones

| Proveedor | Región más cercana a Guatemala | Distancia aprox. | Latencia promedio |
|-----------|-------------------------------|------------------|-------------------|
| **AWS** | us-east-1 (N. Virginia) | ~2,500 km | ~40-60 ms |
| **GCP** | us-central1 (Iowa) | ~3,000 km | ~45-65 ms |
| **Azure** | East US (Virginia) | ~2,500 km | ~40-60 ms |
| **DigitalOcean** | NYC1/NYC3 (Nueva York) | ~3,200 km | ~50-70 ms |

> **Nota**: Ningún proveedor tiene región en Centroamérica. La región más cercana para todos es la costa este de Estados Unidos.

### 4.3.2 Zonas de Disponibilidad

| Proveedor | Zonas de Disponibilidad por Región | SLA de Disponibilidad |
|-----------|-----------------------------------|----------------------|
| **AWS** | 3-6 AZ por región | 99.99% (EC2), 99.95% (RDS Multi-AZ) |
| **GCP** | 3-4 AZ por región | 99.99% (Compute Engine), 99.95% (Cloud SQL) |
| **Azure** | 3+ AZ por región | 99.99% (App Service), 99.99% (Azure SQL) |
| **DigitalOcean** | 3 AZ en algunas regiones | 99.99% (Droplets), 99.95% (Managed DB) |

### 4.3.3 CDN y Edge Computing

| Proveedor | CDN Nativo | Edge Computing | Precio CDN |
|-----------|------------|----------------|------------|
| **AWS** | CloudFront | Lambda@Edge, CloudFront Functions | $0.085/GB (primeros 10TB) |
| **GCP** | Cloud CDN | Cloud Run, Cloud Functions (2nd gen) | $0.02-0.08/GB |
| **Azure** | Azure CDN | Azure Front Door, Azure Functions | $0.081/GB |
| **DigitalOcean** | — (usar Cloudflare) | — | Gratuito con Cloudflare |

### 4.3.4 Cold Start de Funciones Serverless

| Proveedor | Servicio | Java (Spring Boot) | Node.js | Python | Go |
|------------|----------|-------------------|---------|--------|-----|
| **AWS** | Lambda | 3-8 segundos | 0.5-2 segundos | 0.3-1 segundo | 0.2-0.5 segundos |
| **GCP** | Cloud Functions | 2-5 segundos | 0.3-1.5 segundos | 0.2-0.8 segundos | 0.1-0.3 segundos |
| **Azure** | Azure Functions | 4-10 segundos | 0.5-2 segundos | 0.3-1 segundo | — |
| **DigitalOcean** | — | N/A | N/A | N/A | N/A |

> **Nota**: El cold start en Java es significativamente mayor que en otros lenguajes. Para APIs persistentes como UniMarket, se recomienda usar instancias persistentes en lugar de serverless.

---

## 4.4 Experiencia de Desarrollador (4.2.4)

### 4.4.1 Evaluación de Consola Web

| Aspecto | AWS | GCP | Azure | DigitalOcean |
|---------|-----|-----|-------|--------------|
| **Diseño visual** | Funcional pero sobrecargado | Moderno, limpio | Pulido, intuitivo | Simple, minimalista |
| **Navegación** | Compleja, muchos menús | Organizada, clara | Lógica, bien estructurada | Directa, sin complicaciones |
| **Búsqueda de servicios** | Excelente | Buena | Buena | Limitada (menos servicios) |
| **Personalización** | Dashboard personalizable | Paneles personalizables | Dashboard configurable | Básica |
| **Puntuación (1-5)** | 3 | 4 | 4 | 5 |

### 4.4.2 Curva de Aprendizaje

| Proveedor | Tiempo estimado para desplegar primera app | Conceptos nuevos a aprender | Curva (1-5) |
|-----------|------------------------------------------|---------------------------|-------------|
| **AWS** | 3-4 horas | VPC, Security Groups, IAM, EC2, RDS, S3 | 4 (Alta) |
| **GCP** | 2-3 horas | VPC, Firewall Rules, IAM, Compute Engine, Cloud SQL | 3 (Media) |
| **Azure** | 2-3 horas | Resource Groups, App Service, Azure AD, Azure SQL | 3 (Media) |
| **DigitalOcean** | 1-2 horas | Droplets, Managed DB, Spaces | 2 (Baja) |

### 4.4.3 Calidad de Documentación

| Proveedor | Documentación oficial | Tutoriales | Ejemplos de código | Comunidad | Calidad (1-5) |
|-----------|----------------------|------------|-------------------|-----------|---------------|
| **AWS** | Extensa, a veces confusa por cantidad | Muchos, bien estructurados | Abundantes | Enorme | 4 |
| **GCP** | Clara, bien organizada | Buenos, paso a paso | Buenos | Grande | 4 |
| **Azure** | Buena, algo fragmentada | Buenos pero dispersos | Buenos | Grande | 3.5 |
| **DigitalOcean** | Excelente, concisa | Excelentes tutoriales | Limitados | Activa | 5 |

### 4.4.4 CLI y SDKs

| Proveedor | CLI | SDK Java | Calidad CLI (1-5) | Calidad SDK (1-5) |
|-----------|-----|----------|-------------------|-------------------|
| **AWS** | AWS CLI (completo, complejo) | AWS SDK v2 (modular, excelente) | 4 | 5 |
| **GCP** | gcloud (bueno, integrado) | Google Cloud SDK (bueno) | 4 | 4 |
| **Azure** | az (completo) | Azure SDK (completo, verboso) | 3.5 | 4 |
| **DigitalOcean** | doctl (simple, directo) | Spaces SDK (S3-compatible) | 5 | 3 |

### 4.4.5 Impacto en el Trabajo Diario

| Proveedor | Facilita o Dificulta | Razón |
|-----------|---------------------|-------|
| **AWS** | Dificulta al principio, facilita después | Curva pronunciada pero herramientas poderosas |
| **GCP** | Facilita | Interfaz moderna, buenas integraciones |
| **Azure** | Facilita (si usas Microsoft) | Excelente integración con VS Code, GitHub |
| **DigitalOcean** | Facilita mucho | Simplicidad es su mayor fortaleza |

---

## 4.5 Integración con Lenguajes de Programación (4.2.5)

### 4.5.1 Lenguajes Soportados en Serverless

| Lenguaje | AWS Lambda | GCP Cloud Functions | Azure Functions | DigitalOcean |
|----------|------------|---------------------|-----------------|--------------|
| **Java** | ✅ (Corretto 8, 11, 17, 21) | ✅ (GraalVM) | ✅ (Azure Functions Java) | N/A |
| **Node.js** | ✅ (14, 16, 18, 20) | ✅ (18, 20, 22) | ✅ (18, 20) | N/A |
| **Python** | ✅ (3.8-3.12) | ✅ (3.10-3.12) | ✅ (3.8-3.10) | N/A |
| **Go** | ✅ (1.20-1.22) | ✅ (1.21-1.22) | ✅ (Experimental) | N/A |
| **.NET** | ✅ (.NET 6, 7, 8) | ❌ | ✅ (.NET 6, 7, 8) | N/A |

### 4.5.2 Calidad de SDKs Oficiales para Java

| Proveedor | SDK Java | Modularidad | Documentación | Integración Spring | Calidad (1-5) |
|-----------|----------|-------------|---------------|-------------------|---------------|
| **AWS** | `software.amazon.awssdk` v2 | Modular | Excelente | Spring Cloud AWS | 5 |
| **GCP** | `com.google.cloud` | Modular | Buena | Spring Cloud GCP | 4 |
| **Azure** | `com.azure` | Monolítico | Buena | Spring Cloud Azure | 4 |
| **DigitalOcean** | Usa AWS SDK (S3-compatible) | N/A | Comunidad | — | 3 |

### 4.5.3 Restricciones de Runtime

| Proveedor | Restricciones Java | Memoria Máxima | Tiempo Máximo Ejecución |
|-----------|-------------------|----------------|------------------------|
| **AWS Lambda** | Custom runtime para JARs pesados | 10 GB | 15 minutos |
| **GCP Cloud Functions** | GraalVM nativo recomendado | 8 GB (2nd gen) | 60 minutos (2nd gen) |
| **Azure Functions** | Process model | 14 GB | 10 minutos (Consumption) |
| **DigitalOcean** | N/A (instancias persistentes) | Según Droplet | Sin límite |

---

## 4.6 Ecosistema de Herramientas (4.2.6)

### 4.6.1 Catálogo de Servicios Adicionales

| Categoría | AWS | GCP | Azure | DigitalOcean |
|-----------|-----|-----|-------|--------------|
| **Machine Learning** | SageMaker, Bedrock | Vertex AI, Gemini | Azure ML, OpenAI | — (limitado) |
| **IoT** | IoT Core | — (descontinuado) | IoT Hub | — |
| **Análisis de Datos** | Redshift, Athena | BigQuery (excelente) | Synapse Analytics | — |
| **DevOps** | CodePipeline, CodeBuild | Cloud Build | Azure DevOps | — |
| **Kubernetes** | EKS | GKE (el mejor) | AKS | DOKS |
| **Serverless Containers** | Fargate | Cloud Run (excelente) | Container Apps | — |
| **Colas de Mensajes** | SQS, SNS, EventBridge | Pub/Sub | Service Bus, Event Grid | — |
| **Cache** | ElastiCache | Memorystore | Azure Cache for Redis | — |

### 4.6.2 Marketplace y Ecosistema de Terceros

| Proveedor | Marketplace | Integraciones Terceros | Comunidad Open Source |
|-----------|-------------|----------------------|---------------------|
| **AWS** | AWS Marketplace (miles de productos) | Enorme ecosistema | Muy activa |
| **GCP** | GCP Marketplace | Grande ecosistema | Activa |
| **Azure** | Azure Marketplace | Grande ecosistema | Activa |
| **DigitalOcean** | Marketplace limitado | Comunidad activa | Comunidad developer |

---

## Secciones Siguientes

- [5. Requisitos del PoC](05-PoC-Requisitos.md)
- [Índice completo](README.md)
