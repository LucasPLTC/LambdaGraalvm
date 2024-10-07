package br.com.itau.app.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
@Data
public class PostDTO {
    @JsonProperty("resultado")
    private Long userId;

    @JsonProperty("resultado")
    private Long id;

    @JsonProperty("resultado")
    private String title;

    @JsonProperty("resultado")
    private String body;

    // Getters e Setters
    // Se estiver usando Lombok, você pode usar @Data para gerar automaticamente
}
