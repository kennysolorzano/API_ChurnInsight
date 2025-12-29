package com.churninsight.api.dto;

public class StatsResponseDTO {

    private int totalClientes;
    private int clientesEnRiesgo;
    private double tasaChurn;

    public StatsResponseDTO(int totalClientes, int clientesEnRiesgo, double tasaChurn) {
        this.totalClientes = totalClientes;
        this.clientesEnRiesgo = clientesEnRiesgo;
        this.tasaChurn = tasaChurn;
    }

    public int getTotalClientes() {
        return totalClientes;
    }

    public int getClientesEnRiesgo() {
        return clientesEnRiesgo;
    }

    public double getTasaChurn() {
        return tasaChurn;
    }
}
