package com.flightontime.api.dto;

// Note: Nao precisa de @JsonProperty se os nomes dos campos forem identicos ao JSON
public class WeatherResponseDto {
    private String origem;
    private String destino;
    private String data;
    private String condicao;
    private Double temperatura; // Use Double para compatibilidade com JSON (pode ser null)
    private Double vento;       

    // Gerar Getters e Setters para todos os campos (seu IDE pode fazer isso)
    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }
    // ... e assim por diante para destino, data, condicao, temperatura, vento
}
