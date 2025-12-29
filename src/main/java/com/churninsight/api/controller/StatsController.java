package com.churninsight.api.controller;

import com.churninsight.api.dto.ChurnStatsDTO;
import com.churninsight.api.service.StatsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/predict")
@CrossOrigin(origins = "*")
public class StatsController {

    private final StatsService service;

    public StatsController(StatsService service) {
        this.service = service;
    }

    @GetMapping("/charts")
    public ChurnStatsDTO getCharts() {
        return service.getMockStats();
    }
}
