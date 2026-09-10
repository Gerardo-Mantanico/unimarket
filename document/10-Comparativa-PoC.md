# Comparación del PoC - UniMarket CUNOC

## Resumen de Despliegues

| Proveedor | Servicios Utilizados | Costo/Mes | Free Tier | Tiempo Despliegue |
|-----------|---------------------|-----------|-----------|-------------------|
| **AWS** | EC2 + RDS + S3 | $31.61 | 12 meses | 1.5-2 horas |
| **GCP** | Compute Engine + Cloud SQL + Cloud Storage | $23.35 | $300/90 días | 1.5-2 horas |
| **Azure** | App Service + Azure DB + Blob Storage | $66.06 | $200/30 días | 1.5-2 horas |
| **DigitalOcean** | Droplet + Managed DB + Spaces | $46.38 | $200/60 días | 30-45 min |

---

## Ranking por Costo (On-Demand)

| Posición | Proveedor | Costo/Mes | Ahorro vs más caro |
|----------|-----------|-----------|-------------------|
| 🥇 1° | **GCP** | $23.35 | 65% |
| 🥈 2° | **AWS** | $31.61 | 52% |
| 🥉 3° | **DigitalOcean** | $46.38 | 30% |
| 4° | **Azure** | $66.06 | — |

---

## Análisis por Dimensiones

### 1. Precio

| Proveedor | Costo/Mes | Free Tier | Costo Anual |
|-----------|-----------|-----------|-------------|
| **GCP** | $23.35 | $300/90 días | $280.20 |
| **AWS** | $31.61 | 12 meses | $379.32 |
| **DigitalOcean** | $46.38 | $200/60 días | $556.56 |
| **Azure** | $66.06 | $200/30 días | $792.72 |

**Ganador: GCP** (más bajo costo mensual y anual)

### 2. Seguridad

| Proveedor | Certificaciones | Cifrado | IAM | Firewall |
|-----------|-----------------|---------|-----|----------|
| **AWS** | SOC, PCI, ISO, FedRAMP | Sí | IAM detallado | Security Groups |
| **GCP** | SOC, PCI, ISO, HIPAA | Sí | IAM granular | Firewall Rules |
| **Azure** | SOC, PCI, ISO, HIPAA | Sí | Azure AD | NSG |
| **DigitalOcean** | SOC 2 | Sí | Básico | Firewalls |

**Ganador: AWS/GCP** (más certificaciones y opciones)

### 3. Rendimiento

| Proveedor | Compute | Base de Datos | Almacenamiento | Red |
|-----------|---------|---------------|----------------|-----|
| **AWS** | Excelente | RDS (muy estable) | EBS gp3 (rápido) | Global |
| **GCP** | Excelente | Cloud SQL (estable) | Persistent Disk | Global |
| **Azure** | Bueno | Azure DB (estable) | Managed Disks | Global |
| **DigitalOcean** | Bueno | Managed DB (básico) | SSD (rápido) | Regional |

**Ganador: AWS/GCP** (infraestructura más madura)

### 4. Experiencia de Desarrollo (DevEx)

| Proveedor | CLI | SDKs Java | Docs | Curva Aprendizaje |
|-----------|-----|-----------|------|-------------------|
| **AWS** | aws-cli | Completo | Extensa | Empinada |
| **GCP** | gcloud | Completo | Clara | Moderada |
| **Azure** | az | Completo | Clara | Moderada |
| **DigitalOcean** | doctl | Básico | Simple | Suave |

**Ganador: DigitalOcean** (más simple de usar)

### 5. Integración con Java/Spring Boot

| Proveedor | Soporte Java | Spring Initializr | Deploy Fácil |
|-----------|--------------|-------------------|--------------|
| **AWS** | Excelente | Sí (AWS Extension) | Medianamente |
| **GCP** | Excelente | Sí (GCP Extension) | Medianamente |
| **Azure** | Excelente | Sí (Azure Extension) | Sí (App Service) |
| **DigitalOcean** | Manual | No | Manual |

**Ganador: Azure** (App Service simplifica deploy Java)

### 6. Ecosistema

| Proveedor | Servicios | Marketplace | Integraciones |
|-----------|-----------|-------------|---------------|
| **AWS** | 200+ | Extenso | Miles |
| **GCP** | 100+ | Amplio | Muchas |
| **Azure** | 200+ | Extenso | Miles |
| **DigitalOcean** | 15+ | Limitado | Pocas |

**Ganador: AWS/Azure** (ecosistema más grande)

---

## Comparativa Técnica del PoC

### Stack Implementado

| Componente | AWS | GCP | Azure | DigitalOcean |
|------------|-----|-----|-------|--------------|
| **Computación** | EC2 t3.small | e2-small | App Service B1 | Droplet 2vCPU/4GB |
| **Base de Datos** | RDS db.t3.micro | Cloud SQL db-f1-micro | Azure DB B1ms | Managed DB 1GB |
| **Almacenamiento** | S3 Standard | Cloud Storage | Blob Storage | Spaces |
| **Puerto** | 8080 | 8080 | 443 (HTTPS) | 8080 |
| **SSL/TLS** | Manual | Manual | Automático | Manual |

### Configuración de Red

| Aspecto | AWS | GCP | Azure | DigitalOcean |
|---------|-----|-----|-------|--------------|
| **Firewall** | Security Groups | Firewall Rules | NSG + App Service | Firewalls |
| **IP Pública** | Asignada | Asignada | DNS (azurewebsites) | Asignada |
| **Puertos Abiertos** | 22, 8080, 5432 | 22, 8080, 5432 | 443 | 22, 8080 |

### Despliegue de Aplicación

| Método | AWS | GCP | Azure | DigitalOcean |
|--------|-----|-----|-------|--------------|
| **Subida** | SCP | gcloud scp | az webapp deploy | SCP |
| **Ejecución** | nohup java -jar | nohup java -jar | Automático | nohup java -jar |
| **Proceso** | Manual | Manual | Gestionado | Manual |
| **Logs** | app.log | app.log | Log Streaming | app.log |

---

## Costos Detallados

### Desglose por Servicio

| Servicio | AWS | GCP | Azure | DigitalOcean |
|----------|-----|-----|-------|--------------|
| **Computación** | $15.18 | $12.26 | $13.14 | $24.00 |
| **Base de Datos** | $14.71 | $9.29 | $52.83 | $17.38 |
| **Almacenamiento** | $1.72 | $1.80 | $0.09 | $5.00 |
| **TOTAL** | **$31.61** | **$23.35** | **$66.06** | **$46.38** |

### Costos Adicionales

| Concepto | AWS | GCP | Azure | DigitalOcean |
|----------|-----|-----|-------|--------------|
| **Egress (saliente)** | $0.09/GB | $0.12/GB | $0.087/GB | $0.01/GB |
| **Backups** | $0.095/GB/mes | Incluido | $0.095/GB/mes | Incluido |
| **Soporte** | Desde $29/mes | Desde $29/mes | Desde $29/mes | Gratuito |

---

## Recomendación

### Para PoC UniMarket CUNOC:

| Criterio | Proveedor Recomendado | Justificación |
|----------|----------------------|---------------|
| **Menor costo** | **GCP** | $23.35/mes (65% menos que Azure) |
| **Mejor Free Tier** | **AWS** | 12 meses gratis |
| **Mayor simplicidad** | **DigitalOcean** | Interfaz fácil, despliegue rápido |
| **Mejor para Java** | **Azure** | App Service gestiona Java automáticamente |

### Recomendación Final: **Google Cloud Platform (GCP)**

**Justificación:**
1. **Costo más bajo**: $23.35/mes vs $31.61 (AWS) vs $46.38 (DO) vs $66.06 (Azure)
2. **Free Trial generoso**: $300 por 90 días cubre todo el PoC
3. **Rendimiento comparable**: e2-small equivalenta a t3.small
4. **Integración Java**: Soporte completo para Spring Boot
5. **Escalabilidad**: Fácil migración a producción

---

## Conclusiones del PoC

| Aspecto | Conclusión |
|---------|------------|
| **Viabilidad** | Los 4 proveedores permiten desplegar UniMarket exitosamente |
| **Costos** | GCP ofrece la mejor relación costo-beneficio |
| **Complejidad** | DigitalOcean es el más simple, AWS/Azure más complejos |
| **Producción** | GCP o AWS son ideales para escalar a producción |
| **Recomendación** | GCP para el PoC, migrar a AWS si se necesita escalabilidad enterprise |

