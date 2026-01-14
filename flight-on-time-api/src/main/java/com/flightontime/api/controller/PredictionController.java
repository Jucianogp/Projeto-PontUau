package com.flightontime.api.controller;

import com.flightontime.api.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/predict")
public class PredictionController {

    private final PredictionService predictionService;

    @Autowired
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping
    public Map<String, Object> predictFlight(@RequestBody Map<String, Object> payload) {
        return predictionService.predict(payload);
    }
}
