# Guía de Despliegue - GCP (Google Cloud Platform)

## Resumen

| Aspecto | Detalle |
|---------|---------|
| **Proveedor** | Google Cloud Platform (GCP) |
| **Servicios** | Compute Engine + Cloud SQL + Cloud Storage |
| **Costo mensual** | $23.35 |
| **Free Trial** | Sí ($300 por 90 días) |
| **Tiempo estimado** | 1.5 - 2 horas |

---

## 1. Crear Cuenta

1. Ir a https://console.cloud.google.com
2. Click "Get started for free"
3. Iniciar sesión con cuenta de Google
4. Agregar método de pago
5. Activar free trial ($300 créditos por 90 días)

<!-- AGREGAR IMAGEN: gcp_01_crearcuenta.png -->
<!-- Descripción: Google Cloud Console con $300 de créditos -->

---

## 2. Instalar gcloud CLI

```bash
curl https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-cli-linux-x86_64.tar.gz -o gcloud.tar.gz
tar -xf gcloud.tar.gz
./google-cloud-sdk/install.sh
gcloud auth login
gcloud config set project <tu-project-id>
```

<!-- AGREGAR IMAGEN: gcp_02_gcloud.png -->
<!-- Descripción: gcloud CLI instalado y autenticado -->

---

## 3. Crear Firewall Rule

1. Ir a **VPC Network > Firewall > Create firewall rule**
2. Configuración:
   - **Name:** `allow-http-8080`
   - **Direction:** Ingress
   - **Source IP ranges:** 0.0.0.0/0
   - **Protocols and ports:** TCP:8080
3. Click "Create"

<!-- AGREGAR IMAGEN: gcp_03_firewall.png -->
<!-- Descripción: Firewall rule "allow-http-8080" -->

---

## 4. Crear Compute Engine

1. Ir a **Compute Engine > Create instance**
2. Configuración:
   - **Name:** `unimarket-server`
   - **Region:** us-central1
   - **Machine type:** e2-small (2 vCPU, 2 GB RAM)
   - **Boot disk:** Ubuntu 22.04 LTS, 10 GB
   - **Firewall:** Allow HTTP traffic
3. Click "Create"

<!-- AGREGAR IMAGEN: gcp_04_compute.png -->
<!-- Descripción: Compute Engine "unimarket-server" Running -->

---

## 5. Configurar Compute Engine

```bash
gcloud compute ssh unimarket-server --zone=us-central1-a

sudo apt update && sudo apt upgrade -y
sudo apt install -y openjdk-21-jdk
sudo mkdir -p /opt/unimarket
sudo chown ubuntu:ubuntu /opt/unimarket
```

<!-- AGREGAR IMAGEN: gcp_05_java.png -->
<!-- Descripción: Java 21 instalado en Compute Engine -->

---

## 6. Crear Cloud SQL

1. Ir a **SQL > Create instance > PostgreSQL**
2. Configuración:
   - **Instance ID:** `unimarket-db`
   - **Password:** `<tu-password>`
   - **Database version:** PostgreSQL 15
   - **Region:** us-central1
   - **Machine type:** db-f1-micro
   - **Storage:** 10 GB
3. Click "Create"

<!-- AGREGAR IMAGEN: gcp_06_cloudsql.png -->
<!-- Descripción: Cloud SQL "unimarket-db" disponible -->

**Esperar ~5-7 minutos.**

---

## 7. Autorizar IP de Compute Engine

```bash
# Obtener IP externa
gcloud compute instances describe unimarket-server --zone=us-central1-a \
  --format="value(networkInterfaces[0].accessConfigs[0].natIP)"

# Autorizar en Cloud SQL
gcloud sql instances patch unimarket-db \
  --authorized-networks=<compute-engine-ip>/32
```

<!-- AGREGAR IMAGEN: gcp_07_sql_ip.png -->
<!-- Descripción: Cloud SQL con IP autorizada -->

---

## 8. Crear Cloud Storage

```bash
gsutil mb -l us-central1 gs://unimarket-images-gcp/
```

<!-- AGREGAR IMAGEN: gcp_08_storage.png -->
<!-- Descripción: Bucket Cloud Storage creado -->

---

## 9. Subir y Ejecutar Aplicación

```bash
gcloud compute scp target/unimarket-gt-0.0.1-SNAPSHOT.jar \
  unimarket-server:/opt/unimarket/ --zone=us-central1-a

# En Compute Engine
cat > /opt/unimarket/application.properties << 'EOF'
spring.datasource.url=jdbc:postgresql://<cloud-sql-ip>:5432/unimarket
spring.datasource.username=postgres
spring.datasource.password=<tu-password>
spring.flyway.enabled=true
EOF

cd /opt/unimarket
nohup java -jar unimarket-gt-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

<!-- AGREGAR IMAGEN: gcp_09_app.png -->
<!-- Descripción: Spring Boot ejecutándose en GCP -->

---

## 10. Verificar Funcionamiento

```bash
curl -X GET http://<compute-ip>:8080/health
curl -X GET http://<compute-ip>:8080/api/products
```

<!-- AGREGAR IMAGEN: gcp_10_api.png -->
<!-- Descripción: Respuesta JSON de la API en GCP -->

---

## Resumen de Capturas

| # | Archivo | Descripción |
|---|---------|-------------|
| 1 | gcp_01_crearcuenta.png | Google Cloud Console |
| 2 | gcp_02_gcloud.png | gcloud CLI instalado |
| 3 | gcp_03_firewall.png | Firewall rule creada |
| 4 | gcp_04_compute.png | Compute Engine Running |
| 5 | gcp_05_java.png | Java 21 instalado |
| 6 | gcp_06_cloudsql.png | Cloud SQL disponible |
| 7 | gcp_07_sql_ip.png | IP autorizada en SQL |
| 8 | gcp_08_storage.png | Cloud Storage creado |
| 9 | gcp_09_app.png | Spring Boot ejecutándose |
| 10 | gcp_10_api.png | API funcionando |

**Total: 10 capturas**

---

*Guía de despliegue GCP para PoC UniMarket CUNOC.*
