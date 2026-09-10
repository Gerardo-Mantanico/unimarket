# Costos Reales Investigados - PoC UniMarket CUNOC

## Metodología de Investigación

| Aspecto | Detalle |
|---------|---------|
| **Fuentes** | Calculadoras oficiales de cada proveedor |
| **Fecha de consulta** | 13 de septiembre de 2026 |
| **Moneda** | USD |
| **Región base** | US East (AWS/Azure), US Central (GCP), NYC (DO) |

---

# 1. AWS - Costos Investigados

## 1.1 Servicios y Precios (On-Demand)

| Servicio | Configuración | Precio/Hora | Precio/Mes |
|----------|---------------|-------------|------------|
| **EC2 t3.small** | 2 vCPU, 2 GB RAM | $0.0208 | **$15.18** |
| **RDS db.t3.micro** | 2 vCPU, 1 GB RAM, PostgreSQL 15, 20GB | $0.017 | **$12.41** |
| **S3 Standard** | Almacenamiento | — | **$0.023/GB** |
| **Transferencia datos** | Saliente (egress) | — | **$0.09/GB** |
| **EBS gp3** | 20 GB | — | **$1.60** |

### Desglose de Costo Mensual (1 instancia + RDS + 5GB S3)

| Componente | Cálculo | Costo Mensual |
|------------|---------|---------------|
| EC2 t3.small | $0.0208 × 730 hrs | $15.18 |
| RDS db.t3.micro | $0.017 × 730 hrs | $12.41 |
| RDS Storage (20GB) | 20 × $0.115 | $2.30 |
| S3 (5GB) | 5 × $0.023 | $0.12 |
| EBS (20GB) | 20 × $0.08 | $1.60 |
| **TOTAL** | | **$31.61/mes** |

## 1.2 Free Tier (12 meses)

| Servicio | Límite Free Tier | Cubre el PoC |
|----------|------------------|--------------|
| EC2 t3.micro | 750 horas/mes | ✅ Sí |
| RDS db.t3.micro | 750 horas/mes | ✅ Sí |
| S3 | 5 GB | ✅ Sí |

**Costo durante Free Tier: $0.00**

## 1.3 Opciones de Ahorro

| Opción | Ahorro vs On-Demand | Costo EC2 t3.small |
|--------|---------------------|-------------------|
| Spot Instance | ~70% | ~$4.55/mes |
| Reserved 1 año | ~31% | ~$10.47/mes |
| Reserved 3 años | ~50% | ~$7.59/mes |

---

# 2. GCP - Costos Investigados

## 2.1 Servicios y Precios (On-Demand)

| Servicio | Configuración | Precio/Hora | Precio/Mes |
|----------|---------------|-------------|------------|
| **Compute Engine e2-small** | 2 vCPU, 2 GB RAM | $0.0168 | **$12.26** |
| **Cloud SQL db-f1-micro** | 1 vCPU, 0.67 GB RAM, PostgreSQL 15, 10GB | $0.0104 | **$7.59** |
| **Cloud Storage** | Almacenamiento | — | **$0.020/GB** |
| **Transferencia datos** | Saliente (egress) | — | **$0.12/GB** |
| **Persistent Disk** | 10 GB SSD | — | **$1.70** |

### Desglose de Costo Mensual (1 instancia + Cloud SQL + 5GB Storage)

| Componente | Cálculo | Costo Mensual |
|------------|---------|---------------|
| Compute Engine e2-small | $0.0168 × 730 hrs | $12.26 |
| Cloud SQL db-f1-micro | $0.0104 × 730 hrs | $7.59 |
| Cloud SQL Storage (10GB) | 10 × $0.17 | $1.70 |
| Cloud Storage (5GB) | 5 × $0.020 | $0.10 |
| Persistent Disk (10GB) | 10 × $0.17 | $1.70 |
| **TOTAL** | | **$23.35/mes** |

## 2.2 Free Trial ($300 créditos por 90 días)

| Servicio | Límite | Cubre el PoC |
|----------|--------|--------------|
| Compute Engine | $300 en créditos | ✅ Sí |
| Cloud SQL | $300 en créditos | ✅ Sí |
| Cloud Storage | $300 en créditos | ✅ Sí |

**Costo durante Free Trial: $0.00**

## 2.3 Opciones de Ahorro

| Opción | Ahorro vs On-Demand | Costo Compute e2-small |
|--------|---------------------|----------------------|
| Spot Instance | ~40% | ~$7.36/mes |
| CUD 1 año | ~37% | ~$7.72/mes |
| CUD 3 años | ~55% | ~$5.52/mes |
| Sustained Use (automático) | ~20% | ~$9.81/mes |

---

# 3. Azure - Costos Investigados

## 3.1 Servicios y Precios (On-Demand)

| Servicio | Configuración | Precio/Hora | Precio/Mes |
|----------|---------------|-------------|------------|
| **App Service B1** | 1 vCPU, 1.75 GB RAM, Linux | $0.018 | **$13.14** |
| **Azure DB PostgreSQL B1ms** | 1 vCore, 2 GB RAM, 32GB | $0.067 | **$49.15** |
| **Blob Storage** | Almacenamiento | — | **$0.018/GB** |
| **Transferencia datos** | Saliente (egress) | — | **$0.087/GB** |

### Desglose de Costo Mensual (App Service + PostgreSQL + 5GB Blob)

| Componente | Cálculo | Costo Mensual |
|------------|---------|---------------|
| App Service B1 | $0.018 × 730 hrs | $13.14 |
| Azure DB PostgreSQL B1ms | $0.067 × 730 hrs | $49.15 |
| Azure DB Storage (32GB) | 32 × $0.115 | $3.68 |
| Blob Storage (5GB) | 5 × $0.018 | $0.09 |
| **TOTAL** | | **$66.06/mes** |

## 3.2 Free Account ($200 créditos por 30 días)

| Servicio | Límite | Cubre el PoC |
|----------|--------|--------------|
| App Service | $200 créditos | ✅ Sí |
| Azure DB PostgreSQL | $200 créditos | ⚠️ Parcial |
| Blob Storage | $200 créditos | ✅ Sí |

**Costo durante Free Account: $0.00** (si se completa en 30 días)

## 3.3 Servicios Gratuitos (12 meses)

| Servicio | Límite | Cubre el PoC |
|----------|--------|--------------|
| App Service (F1 Free) | 60 CPU min/día, 1 GB RAM | ⚠️ Limitado |
| Azure DB PostgreSQL | 750 horas/mes (B1ms) | ✅ Sí |

## 3.4 Opciones de Ahorro

| Opción | Ahorro vs On-Demand | Costo App Service B1 |
|--------|---------------------|---------------------|
| Reserved 1 año | ~35% | ~$8.54/mes |
| Reserved 3 años | ~55% | ~$5.91/mes |

---

# 4. DigitalOcean - Costos Investigados

## 4.1 Servicios y Precios (On-Demand)

| Servicio | Configuración | Precio/Mes |
|----------|---------------|------------|
| **Droplet Basic** | 2 vCPU, 4 GB RAM, 80 GB SSD | **$24.00** |
| **Managed Database** | 1 vCPU, 1 GB RAM, PostgreSQL 15 | **$15.23** |
| **Spaces** | 250 GB incluidos | **$5.00** |
| **Transferencia datos** | Saliente (egress) | **$0.01/GB** |

### Desglose de Costo Mensual (Droplet + Managed DB + Spaces)

| Componente | Costo Mensual |
|------------|---------------|
| Droplet (2 vCPU, 4GB) | $24.00 |
| Managed Database (1GB) | $15.23 |
| DB Storage adicional | $2.15 |
| Spaces (250GB) | $5.00 |
| **TOTAL** | **$46.38/mes** |

## 4.2 Free Trial ($200 créditos por 60 días)

| Servicio | Límite | Cubre el PoC |
|----------|--------|--------------|
| Droplet | $200 créditos | ✅ Sí (~8 meses) |
| Managed Database | $200 créditos | ✅ Sí (~13 meses) |
| Spaces | $200 créditos | ✅ Sí |

**Costo durante Free Trial: $0.00**

## 4.3 Opciones de Ahorro

| Opción | Ahorro | Costo Droplet |
|--------|--------|---------------|
| Annual Billing | 10% | $21.60/mes |
| 2-year commitment | 20% | $19.20/mes |
| 3-year commitment | 30% | $16.80/mes |

---

# 5. Comparativa de Costos Mensuales

## 5.1 Tabla Comparativa (On-Demand)

| Proveedor | Computación | BD | Almacenamiento | **Total** |
|-----------|-------------|-----|----------------|-----------|
| **AWS** | $15.18 (t3.small) | $14.71 (RDS) | $1.72 | **$31.61** |
| **GCP** | $12.26 (e2-small) | $9.29 (Cloud SQL) | $1.80 | **$23.35** |
| **Azure** | $13.14 (App Service B1) | $52.83 (Azure DB) | $0.09 | **$66.06** |
| **DigitalOcean** | $24.00 (Droplet) | $17.38 (Managed DB) | $5.00 | **$46.38** |

## 5.2 Ranking por Costo

| Posición | Proveedor | Costo/Mes | Ahorro vs más caro |
|----------|-----------|-----------|-------------------|
| 🥇 1° | **GCP** | $23.35 | 65% |
| 🥈 2° | **AWS** | $31.61 | 52% |
| 🥉 3° | **DigitalOcean** | $46.38 | 30% |
| 4° | **Azure** | $66.06 | — |

## 5.3 Costo con Free Tier/Trial

| Proveedor | Free Tier/Trial | Duración | Costo durante prueba |
|-----------|-----------------|----------|---------------------|
| **AWS** | Free Tier | 12 meses | **$0.00** |
| **GCP** | Free Trial ($300) | 90 días | **$0.00** |
| **Azure** | Free Account ($200) | 30 días | **$0.00** |
| **DigitalOcean** | Free Trial ($200) | 60 días | **$0.00** |

## 5.4 Costo Anual Estimado (On-Demand)

| Proveedor | Costo Mensual | Costo Anual |
|-----------|---------------|-------------|
| **GCP** | $23.35 | **$280.20** |
| **AWS** | $31.61 | **$379.32** |
| **DigitalOcean** | $46.38 | **$556.56** |
| **Azure** | $66.06 | **$792.72** |

---

# 6. Costos Ocultos a Considerar

| Factor | AWS | GCP | Azure | DigitalOcean |
|--------|-----|-----|-------|--------------|
| **Egress (saliente)** | $0.09/GB | $0.12/GB | $0.087/GB | $0.01/GB |
| **Backups BD** | $0.095/GB/mes | Incluido (Cloud SQL) | $0.095/GB/mes | Incluido |
| **Snapshots** | $0.05/GB/mes | $0.026/GB/mes | $0.05/GB/mes | $0.06/GB/mes |
| **Soporte técnico** | Desde $29/mes | Desde $29/mes | Desde $29/mes | Gratuito (community) |
| **IP pública** | Incluida | Incluida | Incluida | Incluida |
| **Monitoreo básico** | Gratuito | Gratuito | Gratuito | Gratuito |

---

# 7. Recomendación de Costos

## Para UniMarket CUNOC:

| Escenario | Proveedor Recomendado | Costo Estimado |
|-----------|----------------------|----------------|
| **Desarrollo/Prueba** | Cualquier (free tier) | $0.00 |
| **Producción (bajo tráfico)** | **GCP** | ~$23.35/mes |
| **Producción (tráfico medio)** | AWS o GCP | ~$30-40/mes |
| **Presupuesto más bajo** | **GCP** | $23.35/mes |
| **Simplicidad + precio** | **DigitalOcean** | $46.38/mes |

---
