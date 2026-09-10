# 3. Mapeo de Servicios Equivalentes (Sección 4.1)

## 3.1 Tabla Principal de Mapeo

| Capacidad / Servicio de Referencia (AWS) | Descripción Mínima | AWS | GCP | Azure | DigitalOcean |
|------------------------------------------|-------------------|-----|-----|-------|--------------|
| **EC2** (Compute) | Instancias de máquinas virtuales escalables bajo demanda | Amazon EC2 | Compute Engine | App Service / Virtual Machines | Droplets |
| **S3** (Object Storage) | Almacenamiento de objetos con alta durabilidad y disponibilidad | Amazon S3 | Cloud Storage | Blob Storage | Spaces |
| **RDS** (Managed Relational Database) | Bases de datos relacionales gestionadas (PostgreSQL, MySQL, etc.) | Amazon RDS | Cloud SQL | Azure Database for PostgreSQL | Managed Databases |
| **Lambda** (Serverless / FaaS) | Ejecución de código en respuesta a eventos sin aprovisionar servidores | AWS Lambda | Cloud Functions | Azure Functions | — (no disponible) |
| **VPC** (Networking) | Red privada virtual con control de subnets, tablas de ruteo y firewalls | Amazon VPC | VPC | Virtual Network (VNet) | VPC |
| **IAM** (Identity and Access Management) | Gestión de identidades, roles y permisos de acceso a recursos | AWS IAM | Cloud IAM | Azure Active Directory / RBAC | IAM (limitado) |
| **CloudWatch / CloudTrail** (Observabilidad) | Monitoreo, logging y trazabilidad de operaciones en la nube | CloudWatch + CloudTrail | Cloud Monitoring + Cloud Logging | Azure Monitor + Azure Activity Log | Monitoring |

## 3.2 Servicios Adicionales Relevantes

| Capacidad | AWS | GCP | Azure | DigitalOcean |
|-----------|-----|-----|-------|--------------|
| **CDN** | CloudFront | Cloud CDN | Azure CDN | — (usar Cloudflare) |
| **Balanceo de carga** | ALB / ELB | Cloud Load Balancing | Azure Load Balancer | Load Balancers |
| **DNS** | Route 53 | Cloud DNS | Azure DNS | — |
| **Container Registry** | ECR | Artifact Registry | Azure Container Registry | — |
| **Kubernetes gestionado** | EKS | GKE | AKS | DOKS |
| **CI/CD** | CodePipeline / CodeBuild | Cloud Build | Azure DevOps | — |
| **Secrets Manager** | Secrets Manager | Secret Manager | Key Vault | — |

## 3.3 Detalle por Proveedor

### Amazon Web Services (AWS)

| Servicio | Tipo | Descripción | Región Disponible |
|----------|------|-------------|-------------------|
| Amazon EC2 | IaaS | Instancias de máquinas virtuales personalizables | 31 regiones globales |
| Amazon S3 | SaaS | Almacenamiento de objetos con 99.999999999% durabilidad | Global |
| Amazon RDS | PaaS | Base de datos relacional gestionada (PostgreSQL, MySQL, etc.) | Disponible en todas las regiones EC2 |
| AWS Lambda | FaaS | Funciones serverless con escalado automático | Disponible en todas las regiones |
| Amazon VPC | IaaS | Red privada virtual con control total | Global |
| AWS IAM | SaaS | Gestión de identidades y permisos granular | Global |
| Amazon CloudWatch | SaaS | Monitoreo y observabilidad | Global |

### Google Cloud Platform (GCP)

| Servicio | Tipo | Descripción | Región Disponible |
|----------|------|-------------|-------------------|
| Compute Engine | IaaS | Máquinas virtuales con personalización flexible | 37 regiones globales |
| Cloud Storage | SaaS | Almacenamiento de objetos con múltiples clases | Global |
| Cloud SQL | PaaS | Base de datos relacional gestionada (PostgreSQL, MySQL) | Disponible en todas las regiones |
| Cloud Functions | FaaS | Funciones serverless de ejecución ligera | Disponible en la mayoría de regiones |
| VPC | IaaS | Red privada virtual con subredes globales | Global |
| Cloud IAM | SaaS | Control de acceso granular basado en identidad | Global |
| Cloud Monitoring | SaaS | Monitoreo de infraestructura y aplicaciones | Global |

### Microsoft Azure

| Servicio | Tipo | Descripción | Región Disponible |
|----------|------|-------------|-------------------|
| App Service | PaaS | Plataforma gestionada para aplicaciones web | 60+ regiones |
| Blob Storage | SaaS | Almacenamiento de objetos escalable | Global |
| Azure Database for PostgreSQL | PaaS | PostgreSQL relacional gestionado | Disponible en la mayoría de regiones |
| Azure Functions | FaaS | Funciones serverless con integración .NET | Disponible en la mayoría de regiones |
| Virtual Network (VNet) | IaaS | Red privada virtual con segmentación | Global |
| Azure Active Directory | SaaS | Gestión de identidades y acceso | Global |
| Azure Monitor | SaaS | Monitoreo completo de aplicaciones | Global |

### DigitalOcean

| Servicio | Tipo | Descripción | Región Disponible |
|----------|------|-------------|-------------------|
| Droplets | IaaS | Máquinas virtuales simples y económicas | 15 regiones globales |
| Spaces | SaaS | Almacenamiento de objetos compatible con S3 | 3 regiones (NYC, SFO, AMS) |
| Managed Databases | PaaS | PostgreSQL/MySQL/Redis gestionado | 3 regiones |
| VPC | IaaS | Red privada virtual | Disponible en todas las regiones |
| IAM | SaaS | Control de acceso básico | Global |
| Monitoring | SaaS | Monitoreo básico de métricas | Global |

---

## Secciones Siguientes

- [4. Análisis por Dimensiones Técnicas](04-Analisis-Dimensiones.md)
- [Índice completo](README.md)
