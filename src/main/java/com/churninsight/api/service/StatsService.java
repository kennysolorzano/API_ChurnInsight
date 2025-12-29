package com.churninsight.api.service;

import com.churninsight.api.dto.ChurnStatsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    public ChurnStatsDTO getMockStats() {

        ChurnStatsDTO dto = new ChurnStatsDTO();

        // MOCK 1: clientes en riesgo
        dto.setClientesRiesgo(320);
        dto.setClientesNoRiesgo(680);

        // MOCK 2: probabilidad promedio por mes
        dto.setMeses(List.of(
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio"
        ));

        dto.setProbabilidadPromedio(List.of(
                0.22, 0.25, 0.31, 0.29, 0.35, 0.38
        ));

        return dto;
    }
}
