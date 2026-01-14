package com.flightontime.api.service;

import com.flightontime.api.dto.ModelResponseDto;
import com.flightontime.api.dto.WeatherResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder; // <-- Novo Importe

import java.util.Map;
import java.util.HashMap;
// Importe a classe de exceção para tratamento de erro, se quiser:
// import org.springframework.web.reactive.function.client.WebClientResponseException;


@Service
public class PredictionService {
    // ... (campos @Value e webClient existentes)

    public Map<String, Object> predict(Map<String, Object> payload) {
        
        // 1️⃣ Chamar modelo ML (isso funcionou no teste anterior)
        ModelResponseDto modelResponse = webClient.post()
            .uri(modelApiUrl)
            .bodyValue(payload)
            .retrieve()
            .bodyToMono(ModelResponseDto.class) 
            .block();

        // 2️⃣ Chamar Weather API usando UriComponentsBuilder
        String origem = (String) payload.get("icao_aerodromo_origem");
        String destino = (String) payload.get("icao_aerodromo_destino");
        String data = (String) payload.get("partida_prevista");

        // Construção correta da URL usando UriComponentsBuilder
        String fullWeatherUrl = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
                .queryParam("origem", origem)
                .queryParam("destino", destino)
                .queryParam("data", data)
                .toUriString();

        WeatherResponseDto weatherResponse = webClient.get()
            .uri(fullWeatherUrl) // Use a URL construída corretamente
            .retrieve()
            .bodyToMono(WeatherResponseDto.class) 
            .block(); // <-- Linha 39 atualizada

        // 3️⃣ Combinar os resultados
        Map<String, Object> result = new HashMap<>();
        result.put("model", modelResponse); 
        result.put("weather", weatherResponse);

        return result;
    }
}
