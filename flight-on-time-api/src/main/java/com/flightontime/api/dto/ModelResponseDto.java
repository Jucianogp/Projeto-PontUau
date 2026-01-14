package com.flightontime.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelResponseDto {
    // Usamos @JsonProperty porque o JSON usa snake_case (previsao_atraso) 
    // e o Java usa camelCase (previsaoAtraso)

    @JsonProperty("previsao_atraso")
    private Integer previsaoAtraso;
    
    @JsonProperty("probabilidade_atraso")
    private Double probabilidadeAtraso;

    // Gerar Getters e Setters para todos os campos
    public Integer getPrevisaoAtraso() { return previsaoAtraso; }
    public void setPrevisaoAtraso(Integer previsaoAtraso) { this.previsaoAtraso = previsaoAtraso; }
    // ... e assim por diante para probabilidadeAtraso
}
