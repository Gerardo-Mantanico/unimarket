# Guía de Despliegue - DigitalOcean

## Resumen

| Aspecto | Detalle |
|---------|---------|
| **Proveedor** | DigitalOcean |
| **Servicios** | Droplet + Managed Database + Spaces |
| **Costo mensual** | $46.38 |
| **Free Trial** | Sí ($200 por 60 días) |
| **Tiempo estimado** | 30-45 minutos |

---

## 1. Crear Cuenta

1. Ir a https://www.digitalocean.com
2. Click "Sign up"
3. Ingresar email y contraseña
4. Verificar email
5. Agregar método de pago
6. Activar free trial ($200 créditos por 60 días)

<!-- AGREGAR IMAGEN: do_01_crearcuenta.png -->
<!-- Descripción: Panel DigitalOcean con $200 créditos -->

---

## 2. Crear SSH Key

```bash
ssh-keygen -t ed25519 -C "tu@email.com"
```

En el panel:
1. **Settings > Security > SSH Keys > Add SSH Key**
2. Nombre: `unimarket-key`
3. Pegar contenido de `~/.ssh/id_ed25519.pub`

<!-- AGREGAR IMAGEN: do_02_sshkey.png -->
<!-- Descripción: SSH Key "unimarket-key" agregada -->

---

## 3. Crear Firewall

1. Ir a **Networking > Firewalls > Create Firewall**
2. Nombre: `unimarket-fw`
3. Inbound Rules:

| Tipo | Puerto | Origen |
|------|--------|--------|
| SSH | 22 | 0.0.0.0/0 |
| Custom | 8080 | 0.0.0.0/0 |

<!-- AGREGAR IMAGEN: do_03_firewall.png -->
<!-- Descripción: Firewall "unimarket-fw" configurado -->

---

## 4. Crear Droplet

1. Ir a **Droplets > Create > Droplets**
2. Configuración:
   - **Image:** Ubuntu 22.04
   - **Plan:** Basic - 2 vCPU, 4 GB RAM ($24/mes)
   - **Region:** NYC1
   - **Authentication:** SSH keys > `unimarket-key`
   - **Hostname:** `unimarket-server`
3. Click "Create Droplet"

<!-- AGREGAR IMAGEN: do_04_droplet.png -->
<!-- Descripción: Droplet "unimarket-server" con IP asignada -->

---

## 5. Configurar Droplet

```bash
ssh root@<droplet-ip>

apt update && apt upgrade -y
apt install -y openjdk-21-jdk
mkdir -p /opt/unimarket
```

<!-- AGREGAR IMAGEN: do_05_java.png -->
<!-- Descripción: Java 21 instalado en Droplet -->

---

## 6. Crear Managed Database

1. Ir a **Databases > Create Database Cluster**
2. Configuración:
   - **Engine:** PostgreSQL 15
   - **Plan:** Basic - 1 vCPU, 1 GB RAM ($15/mes)
   - **Datacenter:** NYC1
   - **Name:** `unimarket-db`
3. Click "Create Database Cluster"

<!-- AGREGAR IMAGEN: do_06_database.png -->
<!-- Descripción: Managed Database "unimarket-db" Online -->

**Esperar ~3-5 minutos.**

---

## 7. Crear Space

1. Ir a **Spaces > Create a Space**
2. **Region:** NYC1
3. **Name:** `unimarket-images`
4. Click "Create Space"

<!-- AGREGAR IMAGEN: do_07_spaces.png -->
<!-- Descripción: Space "unimarket-images" creado -->

**Generar Access Key en API > Tokens/Keys > Generate New Token**

---

## 8. Subir y Ejecutar Aplicación

```bash
scp target/unimarket-gt-0.0.1-SNAPSHOT.jar root@<droplet-ip>:/opt/unimarket/

# En Droplet
cat > /opt/unimarket/application.properties << 'EOF'
spring.datasource.url=jdbc:postgresql://<db-host>:25060/unimarket
spring.datasource.username=doadmin
spring.datasource.password=<tu-password>
spring.flyway.enabled=true
EOF

cd /opt/unimarket
nohup java -jar unimarket-gt-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

<!-- AGREGAR IMAGEN: do_08_app.png -->
<!-- Descripción: Spring Boot ejecutándose en Droplet -->

---

## 9. Verificar Funcionamiento

```bash
curl -X GET http://<droplet-ip>:8080/health
curl -X GET http://<droplet-ip>:8080/api/products
```

<!-- AGREGAR IMAGEN: do_09_api.png -->
<!-- Descripción: Respuesta JSON de la API en DigitalOcean -->

---

## Resumen de Capturas

| # | Archivo | Descripción |
|---|---------|-------------|
| 1 | do_01_crearcuenta.png | Panel DigitalOcean |
| 2 | do_02_sshkey.png | SSH Key agregada |
| 3 | do_03_firewall.png | Firewall configurado |
| 4 | do_04_droplet.png | Droplet con IP |
| 5 | do_05_java.png | Java 21 instalado |
| 6 | do_06_database.png | Managed Database Online |
| 7 | do_07_spaces.png | Space creado |
| 8 | do_08_app.png | Spring Boot ejecutándose |
| 9 | do_09_api.png | API funcionando |

**Total: 9 capturas**

---

*Guía de despliegue DigitalOcean para PoC UniMarket CUNOC.*
