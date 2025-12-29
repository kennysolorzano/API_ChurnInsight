package com.churninsight.api.dto;

import java.util.List;

public class ChurnStatsDTO {

    private int clientesRiesgo;
    private int clientesNoRiesgo;

    private List<String> meses;
    private List<Double> probabilidadPromedio;

    // Getters y setters
    public int getClientesRiesgo() {
        return clientesRiesgo;
    }

    public void setClientesRiesgo(int clientesRiesgo) {
        this.clientesRiesgo = clientesRiesgo;
    }

    public int getClientesNoRiesgo() {
        return clientesNoRiesgo;
    }

    public void setClientesNoRiesgo(int clientesNoRiesgo) {
        this.clientesNoRiesgo = clientesNoRiesgo;
    }

    public List<String> getMeses() {
        return meses;
    }

    public void setMeses(List<String> meses) {
        this.meses = meses;
    }

    public List<Double> getProbabilidadPromedio() {
        return probabilidadPromedio;
    }

    public void setProbabilidadPromedio(List<Double> probabilidadPromedio) {
        this.probabilidadPromedio = probabilidadPromedio;
    }
}
