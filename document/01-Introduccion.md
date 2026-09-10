# 1. Introducción

## 1.1 Contexto

La computación en la nube ha consolidado el modelo predominante para el despliegue de sistemas de software a escala global. Sin embargo, la oferta de proveedores es amplia y heterogénea; cada plataforma presenta diferencias significativas en costos, modelos de seguridad, rendimiento, experiencia de desarrollo, ecosistema de servicios gestionados y compatibilidad con lenguajes de programación.

La selección de un proveedor de nube no es una decisión meramente comercial: condiciona la arquitectura, el presupuesto operativo, la postura de seguridad y la velocidad de entrega del equipo de desarrollo.

## 1.2 Caso de Estudio: UniMarket CUNOC

El proyecto **UniMarket CUNOC** es una plataforma de marketplace universitario desarrollada con **Java 21** y **Spring Boot 4.1.1**, utilizando **PostgreSQL** como base de datos principal y **Flyway** para migraciones. El sistema gestiona productos, categorías, documentos e imágenes, con integración nativa a servicios de almacenamiento en la nube (AWS S3, Azure Blob Storage, Google Cloud Storage).

### Componentes del Sistema

- **API REST**: Endpoints para gestión de productos, categorías y documentos
- **Base de datos**: PostgreSQL con migraciones Flyway
- **Almacenamiento**: Integración con S3, Azure Blob y GCP Storage
- **Documentación API**: Springdoc OpenAPI (Swagger UI)

## 1.3 Objetivos del PoC

1. Desplegar UniMarket CUNOC en cuatro proveedores de nube diferentes
2. Documentar el proceso, tiempos, dificultades y costos reales
3. Comparar empíricamente la experiencia de despliegue
4. Fundamentar una recomendación técnica basada en evidencia

## 1.4 Justificación del Stack Tecnológico

| Componente | Tecnología | Versión | Justificación |
|------------|------------|---------|---------------|
| **Lenguaje** | Java | 21 LTS | Soporte a largo plazo, mejoras de rendimiento, amplia adopción empresarial |
| **Framework** | Spring Boot | 4.1.1 | Ecosistema maduro, soporte nativo para cloud-native, integración con todos los proveedores |
| **Base de datos** | PostgreSQL | 15 | Open source, disponible como servicio gestionado en los 4 proveedores |
| **Migraciones** | Flyway | (via Spring Boot) | Control de versiones de esquema, desplegable en cualquier entorno |
| **API Docs** | Springdoc OpenAPI | 2.8.11 | Documentación automática de endpoints, UI Swagger integrada |
| **Almacenamiento** | S3-compatible | — | API estándar de la industria para objetos |

### ¿Por qué Java/Spring Boot?

- **Madurez**: Spring Boot tiene más de 10 años de evolución y comunidad activa
- **Cloud-native**: Soporte nativo para Kubernetes, Docker, y servicios de nube
- **Rendimiento**: JVM optimizada para cargas de trabajo de servidor
- **Habilidades transferibles**: Java es el lenguaje más utilizado en empresas enterprise
- **Integración**: SDKs oficiales para AWS, GCP, Azure y DigitalOcean

---

## Secciones Siguientes

- [2. Arquitectura de UniMarket CUNOC](02-Arquitectura.md)
- [Índice completo](README.md)
