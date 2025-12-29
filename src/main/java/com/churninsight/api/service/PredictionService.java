package com.churninsight.api.service;


import com.churninsight.api.dto.PredictionRequestDTO;
import com.churninsight.api.dto.PredictionResponseDTO;
import com.churninsight.api.dto.StatsResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {


        public PredictionResponseDTO predict(PredictionRequestDTO request) {

            int retrasos = request.getRetrasosPago() != null ? request.getRetrasosPago() : 0;
            int meses = request.getTiempoContratoMeses() != null ? request.getTiempoContratoMeses() : 0;
            double uso = request.getUsoMensual() != null ? request.getUsoMensual() : 0;

            boolean altoRiesgo =
                    retrasos > 2 ||
                            meses < 6 ||
                            uso < 10;

            if (altoRiesgo) {
                return new PredictionResponseDTO("Va a cancelar", 0.75);
            } else {
                return new PredictionResponseDTO("Va a continuar", 0.15);
            }
        }

    public PredictionResponseDTO predictByClientId(String clientId) {
        return new PredictionResponseDTO("Va a cancelar", 0.82);
    }


    public StatsResponseDTO getStats() {
        return new StatsResponseDTO(500, 120, 0.24);
    }
}

