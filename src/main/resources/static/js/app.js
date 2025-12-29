// ---------- CAMBIO DE SECCIONES ----------
function cambiarTab(btn, id) {

    document.querySelectorAll(".seccion").forEach(sec => {
        sec.classList.add("hidden");
    });

    document.getElementById(id).classList.remove("hidden");

    document.querySelectorAll(".tab-btn").forEach(b => {
        b.classList.remove("active");
    });

    btn.classList.add("active");
}

// ---------- FORMULARIO CÁLCULO MANUAL ----------
document.getElementById("churnForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const data = {
        tiempoContratoMeses: parseInt(document.getElementById("tiempoContratoMeses").value),
        retrasosPago: parseInt(document.getElementById("retrasosPago").value),
        usoMensual: parseFloat(document.getElementById("usoMensual").value),
        plan: document.getElementById("plan").value
    };

    // 🔹 ACTUALIZAR PANEL API
    const apiPreview = document.getElementById("apiPreview");
    apiPreview.textContent = `
POST /predict
Content-Type: application/json

{
  "tiempo_contrato_meses": ${data.tiempoContratoMeses},
  "retrasos_pago": ${data.retrasosPago},
  "uso_mensual": ${data.usoMensual},
  "plan": "${data.plan}"
}
`;

    try {
        const response = await fetch("http://localhost:8080/predict", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        const divResultado = document.getElementById("resultado");
        divResultado.classList.remove("hidden");
        divResultado.innerHTML = `
            <h3>Resultado</h3>
            <p><strong>Predicción:</strong> ${result.prevision}</p>
            <p><strong>Probabilidad:</strong> ${(result.probabilidad * 100).toFixed(2)}%</p>
        `;

    } catch (error) {
        alert("Error al conectar con la API");
    }
});

// ---------- BÚSQUEDA POR ID ----------
async function buscarCliente() {
    const id = document.getElementById("clientId").value;

    if (!id) {
        alert("Ingresa un ID de cliente");
        return;
    }

    const response = await fetch(`/predict/client/${id}`);
    const result = await response.json();

    const div = document.getElementById("resultadoBusqueda");
    div.classList.remove("hidden");
    div.innerHTML = `
        <h3>Resultado Cliente ${id}</h3>
        <p><strong>Predicción:</strong> ${result.prevision}</p>
        <p><strong>Probabilidad:</strong> ${(result.probabilidad * 100).toFixed(2)}%</p>
    `;
}

// ---------- ESTADÍSTICAS ----------
let chartRiesgo = null;
let chartMeses = null;

async function cargarGraficas() {

    const response = await fetch("http://localhost:8080/predict/charts");
    const data = await response.json();

    if (chartRiesgo) chartRiesgo.destroy();
    if (chartMeses) chartMeses.destroy();

    chartRiesgo = new Chart(document.getElementById("chartRiesgo"), {
        type: "pie",
        data: {
            labels: ["En riesgo", "No en riesgo"],
            datasets: [{
                data: [
                    data.clientesRiesgo,
                    data.clientesNoRiesgo
                ]
            }]
        }
    });

    chartMeses = new Chart(document.getElementById("chartMeses"), {
        type: "line",
        data: {
            labels: data.meses,
            datasets: [{
                label: "Probabilidad promedio",
                data: data.probabilidadPromedio
            }]
        }
    });
}

// ---------- ESTADO INICIAL ----------
document.addEventListener("DOMContentLoaded", () => {
    cambiarTab(document.querySelector(".tab-btn"), "manual");
});
