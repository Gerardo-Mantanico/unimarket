# 10. Comparativa Empírica (Sección 4.3.2)

## 10.1 Tabla Resumen de Resultados

| Criterio | AWS | GCP | Azure | DigitalOcean |
|----------|-----|-----|-------|--------------|
| **Tiempo hasta primer despliegue** | 3h 15min | 2h 45min | 2h 30min | 1h 30min |
| **Curva de aprendizaje (1-5)** | 4 (Alta) | 3 (Media) | 3 (Media-Baja) | 2 (Baja) |
| **Dificultad general (1-5)** | 4 (Alta) | 3 (Media) | 3 (Media) | 2 (Baja) |
| **Costo real incurrido** | $0.00 | $0.00 | $0.00 | $0.00 |
| **Costo estimado/mes** | ~$27.80 | ~$19.93 | ~$103.98 | ~$38.00 |
| **Calidad documentación (1-5)** | 4 | 4 | 3.5 | 5 |
| **Consola web (1-5)** | 3 | 4 | 4 | 5 |
| **CLI (1-5)** | 4 | 4 | 3.5 | 5 |
| **Fiabilidad del servicio (1-5)** | 5 | 5 | 5 | 4.5 |

## 10.2 Análisis Cualitativo por Proveedor

### AWS
- **Puntos fuertes**: Servicios más completos, mayor ecosistema, mejor documentación técnica
- **Puntos débiles**: Curva de aprendizaje pronunciada, consola sobrecargada, costos más altos
- **Sorpresa**: La configuración de permisos IAM es más compleja de lo esperado

### GCP
- **Puntos fuertes**: Mejor relación precio-calidad, consola moderna, $300 créditos generosos
- **Puntos débiles**: Menor cuota de mercado, menos regiones en LATAM
- **Sorpresa**: Cloud SQL es muy fácil de configurar comparado con RDS

### Azure
- **Puntos fuertes**: Buena integración con herramientas Microsoft, despliegue rápido desde CLI
- **Puntos débiles**: Significativamente más caro, configuración de App Service confusa para Java
- **Sorpresa**: El costo de App Service B1 es muy alto comparado con EC2/Compute Engine

### DigitalOcean
- **Puntos fuertes**: Simplicidad extrema, documentación excelente, precios predecibles
- **Puntos débiles**: Ecosistema limitado, sin serverless, menos regiones
- **Sorpresa**: Spaces es completamente compatible con el SDK de S3

## 10.3 Comparación con Documentación Teórica

| Aspecto | Teoría | Práctica | Diferencia |
|---------|--------|----------|------------|
| **Costos** | Free tier cubre todo | Free tier tiene límites estrictos | Los costos post-free tier son significativamente más altos |
| **Tiempo de despliegue** | ~30 min por proveedor | 1.5-3 horas por proveedor | 3-6x más tiempo del estimado |
| **Configuración de BD** | "Un clic para crear" | Requiere configuración detallada de redes, permisos | Más complejo de lo documentado |
| **Seguridad** | Configurar al mínimo | Requiere conocimiento profundo de IAM/firewalls | La seguridad es más compleja de lo esperado |
| **Documentación** | "Extensa y completa" | A veces confusa por cantidad de información | La calidad varía significativamente |

---

## Secciones Siguientes

- [11. Recomendación Fundamentada](11-Recomendacion.md)
- [Índice completo](README.md)
