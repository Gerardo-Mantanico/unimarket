# Guía de Despliegue - AWS (Amazon Web Services)

## Resumen

| Aspecto | Detalle |
|---------|---------|
| **Proveedor** | Amazon Web Services (AWS) |
| **Servicios** | EC2 + RDS + S3 |
| **Costo mensual** | $31.61 |
| **Free Tier** | Sí (12 meses) |
| **Tiempo estimado** | 1.5 - 2 horas |

---

## 1. Crear Cuenta

1. Ir a https://aws.amazon.com → "Create an AWS Account"
2. Ingresar email, contraseña y nombre de cuenta
3. Agregar método de pago (tarjeta)
4. Verificar identidad por teléfono
5. Seleccionar plan: **Basic (Free)**

<!-- AGREGAR IMAGEN: aws_01_crearcuenta.png -->
<!-- Descripción: Pantalla de registro AWS -->

---

## 2. Crear Key Pair (Clave SSH)

1. Ir a **EC2 > Key Pairs > Create key pair**
2. Nombre: `unimarket-key`
3. Tipo: RSA | Formato: .pem
4. Descargar y guardar el archivo `.pem`

```bash
chmod 400 unimarket-key.pem
```

<!-- AGREGAR IMAGEN: aws_02_keypair.png -->
<!-- Descripción: Key Pair "unimarket-key" creado -->

---

## 3. Crear Security Group

1. Ir a **EC2 > Security Groups > Create security group**
2. Nombre: `unimarket-sg`
3. Agregar reglas de entrada:

| Tipo | Puerto | Origen |
|------|--------|--------|
| SSH | 22 | 0.0.0.0/0 |
| Custom TCP | 8080 | 0.0.0.0/0 |
| PostgreSQL | 5432 | self (sg-xxx) |

<!-- AGREGAR IMAGEN: aws_03_securitygroup.png -->
<!-- Descripción: Security Group con reglas configuradas -->

---

## 4. Crear Instancia EC2

1. Ir a **EC2 > Launch instances**
2. Configuración:
   - **Name:** `unimarket-server`
   - **AMI:** Ubuntu 22.04 LTS
   - **Instance type:** t3.small (2 vCPU, 2 GB RAM)
   - **Key pair:** `unimarket-key`
   - **Security group:** `unimarket-sg`
   - **Storage:** 20 GB gp3
3. Click "Launch instance"

<!-- AGREGAR IMAGEN: aws_04_ec2.png -->
<!-- Descripción: EC2 "unimarket-server" en estado Running -->

---

## 5. Configurar EC2

```bash
ssh -i "unimarket-key.pem" ubuntu@<ec2-public-ip>

# Instalar Java 21
sudo apt update && sudo apt upgrade -y
sudo apt install -y openjdk-21-jdk

# Crear directorio
sudo mkdir -p /opt/unimarket
sudo chown ubuntu:ubuntu /opt/unimarket
```

<!-- AGREGAR IMAGEN: aws_05_java_instalado.png -->
<!-- Descripción: Terminal con Java 21 instalado -->

---

## 6. Crear RDS PostgreSQL

1. Ir a **RDS > Create database**
2. Configuración:
   - **Engine:** PostgreSQL 15
   - **Template:** Free tier
   - **DB instance identifier:** `unimarket-db`
   - **Master username:** `admin`
   - **Master password:** `<tu-password>`
   - **Instance class:** db.t3.micro
   - **Storage:** 20 GB
   - **Public access:** Yes
   - **Initial database name:** `unimarket`
3. Click "Create database"

<!-- AGREGAR IMAGEN: aws_06_rds.png -->
<!-- Descripción: RDS "unimarket-db" con estado Available -->

**Esperar ~5-10 minutos hasta que el estado sea "Available".**

---

## 7. Configurar Security Group de RDS

1. Ir a **EC2 > Security Groups**
2. Buscar el SG de RDS (creado automáticamente)
3. Editar reglas de entrada:
   - Tipo: PostgreSQL
   - Puerto: 5432
   - Origen: `unimarket-sg` (el SG de EC2)

<!-- AGREGAR IMAGEN: aws_07_rds_sg.png -->
<!-- Descripción: SG de RDS permitiendo tráfico desde EC2 -->

---

## 8. Crear Bucket S3

1. Ir a **S3 > Create bucket**
2. Configuración:
   - **Bucket name:** `unimarket-images`
   - **Region:** US East (N. Virginia)
3. Click "Create bucket"

<!-- AGREGAR IMAGEN: aws_08_s3.png -->
<!-- Descripción: Bucket S3 "unimarket-images" creado -->

---

## 9. Subir y Ejecutar Aplicación

```bash
# Subir JAR desde tu máquina local
scp -i "unimarket-key.pem" target/unimarket-gt-0.0.1-SNAPSHOT.jar \
  ubuntu@<ec2-ip>:/opt/unimarket/

# En EC2: crear application.properties
cat > /opt/unimarket/application.properties << 'EOF'
spring.datasource.url=jdbc:postgresql://<rds-endpoint>:5432/unimarket
spring.datasource.username=admin
spring.datasource.password=<tu-password>
spring.flyway.enabled=true
cloud.aws.region.static=us-east-1
unimarket.s3.bucket-name=unimarket-images
EOF

# Ejecutar
cd /opt/unimarket
nohup java -jar unimarket-gt-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

<!-- AGREGAR IMAGEN: aws_09_app_ejecutando.png -->
<!-- Descripción: Spring Boot ejecutándose en EC2 -->

---

## 10. Verificar Funcionamiento

```bash
curl -X GET http://<ec2-ip>:8080/health
curl -X GET http://<ec2-ip>:8080/api/products
```

<!-- AGREGAR IMAGEN: aws_10_api_funcionando.png -->
<!-- Descripción: Respuesta JSON de la API en AWS -->

---

## Resumen de Capturas

| # | Archivo | Descripción |
|---|---------|-------------|
| 1 | aws_01_crearcuenta.png | Pantalla de registro AWS |
| 2 | aws_02_keypair.png | Key Pair creado |
| 3 | aws_03_securitygroup.png | Security Group configurado |
| 4 | aws_04_ec2.png | EC2 en estado Running |
| 5 | aws_05_java_instalado.png | Java 21 instalado |
| 6 | aws_06_rds.png | RDS disponible |
| 7 | aws_07_rds_sg.png | SG de RDS configurado |
| 8 | aws_08_s3.png | Bucket S3 creado |
| 9 | aws_09_app_ejecutando.png | Spring Boot ejecutándose |
| 10 | aws_10_api_funcionando.png | API funcionando |

**Total: 10 capturas**

---

*Guía de despliegue AWS para PoC UniMarket CUNOC.*
