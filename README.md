<div align="center">
  <img
    src="https://github.com/user-attachments/assets/cb3f82c7-9cbc-4a10-ab12-1efc4e9a5828"
    alt="Churn Insight Logo"
    width="200"
  />
</div>

<h1 align="center">📊 Churn Insight</h1>

<h3 align="center">Plataforma de análisis y predicción de cancelación de clientes</h3>

---

## 🧠 Descripción

**Churn Insight** es una plataforma web para analizar y predecir *customer churn* mediante un modelo de Machine Learning.

Este repositorio contiene **un Backend en Spring Boot** que actúa como **API Gateway** hacia el microservicio ML (FastAPI) y además sirve un **Frontend web responsivo** (HTML/CSS/JS + Chart.js) con dashboard y exportación a PDF.

---

## 🏗️ Arquitectura

```text
[ Frontend Web (Thymeleaf + Static: HTML/CSS/JS + Chart.js) ]
                    ↓ (consume JSON normalizado)
[ Backend Spring Boot (API Gateway + Normalización/Resiliencia) ]
                    ↓ (HTTP)
[ Microservicio ML Externo (FastAPI / IA Model) ]
```

> El microservicio de ML **no viene en este repositorio**. El backend se configura para apuntar al servicio ML (por IP, dominio o Cloudflare Tunnel).

---

## 🧩 Funcionalidades

### 🔹 Frontend

- **Predicción individual (Cálculo Manual):** formulario para estimar riesgo de churn.
- **Búsqueda por ID:** consulta de un cliente por `publicId`.
- **Análisis avanzado:** dashboard con 4 visualizaciones.
- **Exportación:** genera un **PDF** con **1 gráfico por página**, con logo y títulos.
- **UX/Resiliencia:** manejo de errores (por ejemplo 404/500) con mensajes claros.

Tecnologías: **HTML5 / CSS3 / JS (ES6+) / Chart.js / jsPDF**.

### 🔹 Backend (Spring Boot)

- **API Gateway:** orquesta llamadas al servicio ML.
- **Normalización y tolerancia a formatos:**
  - Soporta respuestas del modelo en formato **objeto** (`{ totalUsers, data: [...] }`) o **lista** (`[...]`).
  - Convierte tipos comunes (por ejemplo números como `string`, valores con `%`, etc.) y aplica defaults seguros.
  - Si el servicio externo falla o responde inconsistente, retorna estructuras vacías para que la UI no colapse.
- **Documentación:** Swagger UI disponible en `/swagger-ui/index.html`.

Tecnologías: **Java 17**, **Spring Boot 3.5.8**, **Maven**, **RestTemplate**, **Lombok**, **Thymeleaf**.

---

## 🔌 Endpoints principales

### 📍 Predicción

- `POST /predict` → predicción manual.
- `GET /predict/client/{publicId}` → perfil + riesgo de un cliente.

### 📈 Estadísticas (dashboard)

- `GET /probability/gender`
- `GET /probability/region`
- `GET /probability/subscription`
- `GET /probability/age`

---

## ⚙️ Configuración del servicio ML

El backend toma la URL del servicio ML desde variables de entorno (o `application.properties`).

| Variable | Descripción | Default |
|---|---|---|
| `PORT` | Puerto del backend | `8080` |
| `ML_BASE_URL` | Base URL para endpoints de predicción | `http://168.197.48.239:8000` |
| `ML_STATS_BASE_URL` | Base URL para endpoints de estadísticas (opcional) | igual a `ML_BASE_URL` |
| `ML_PREDICT_PATH` | Path de predicción manual | `/predict` |
| `ML_PREDICT_BY_ID_PATH` | Prefijo del endpoint por ID | `/item/predictions/` |
| `ML_STATS_PATH_PREFIX` | Prefijo para estadísticas | `/probability/` |

Ejemplos:

```bash
# (Linux/macOS)
export ML_BASE_URL="https://<tu-subdominio>.trycloudflare.com"
./mvnw spring-boot:run
```

```powershell
# (Windows PowerShell)
$env:ML_BASE_URL = "https://<tu-subdominio>.trycloudflare.com"
./mvnw spring-boot:run
```

### Frontend y URL de API

Por defecto, el frontend usa **mismo origen** (llama a `/predict` y `/probability/...` sin dominio), por lo que funciona tanto en local como detrás de un reverse proxy. Si necesitas apuntar a otra URL, ajusta la constante `API_BASE` en `src/main/resources/static/js/app.js`.

---

## 🚀 Ejecución local

Requisitos: **Java 17**.

```bash
./mvnw clean spring-boot:run
```

Luego abre:

- App: `http://localhost:8080/`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI: `http://localhost:8080/v3/api-docs`

---

## 🐳 Ejecución con Docker

1) Construir el JAR:

```bash
./mvnw clean package -DskipTests
```

2) Construir la imagen:

```bash
docker build -t churninsight-api .
```

3) Ejecutar:

```bash
docker run --rm -p 8080:8080 \
  -e ML_BASE_URL="https://<tu-subdominio>.trycloudflare.com" \
  churninsight-api
```

---

## 🧪 Formatos esperados del servicio ML (referencia)

### Estadísticas

```json
{
  "totalUsers": 1000,
  "data": [
    {
      "label": "Male",
      "churnProbability": 22.4,
      "notChurnProbability": 77.6,
      "usersCount": 500
    }
  ]
}
```

### Predicción por ID

```json
{
  "prediction": {
    "prediction": 1,
    "probabilities": { "churn": 0.82, "not_churn": 0.18 }
  },
  "data": [
    { "publicId": "CUS-6BF81F27", "age": 52, "gender": "Male" }
  ]
}
```

> El backend es tolerante ante variaciones típicas (por ejemplo lista directa en stats, números como strings, valores con `%`, etc.).

---

## 👥 Equipo DracoStack

- **Hernán Cerda** — Backend & Integración.
- **Silvia Hernández** — Backend & Arquitectura.
- **Aldo Sánchez** — Backend & ML Connection.

---

<div align="center">
  <p><i>Integración de Spring Boot + Machine Learning con enfoque en UX y resiliencia de datos.</i></p>
</div>
