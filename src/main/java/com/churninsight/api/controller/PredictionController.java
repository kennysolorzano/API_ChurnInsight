package com.churninsight.api.controller;


import com.churninsight.api.dto.PredictionRequestDTO;
import com.churninsight.api.dto.PredictionResponseDTO;
import com.churninsight.api.dto.StatsResponseDTO;
import com.churninsight.api.service.PredictionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/predict")
public class PredictionController {

    private final PredictionService service;

    public PredictionController(PredictionService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "API OK";
    }


    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<PredictionResponseDTO> predict(
            @Valid @RequestBody PredictionRequestDTO request) {

        return ResponseEntity.ok(service.predict(request));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<PredictionResponseDTO> predictByClientId(
            @PathVariable String id) {

        return ResponseEntity.ok(service.predictByClientId(id));
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponseDTO> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

}
