package br.com.itau.app.dto;

import lombok.Data;

@Data
public class PostDTO {
    private Long userId;
    private Long id;
    private String title;
    private String body;

    // Getters e Setters
    // Se estiver usando Lombok, você pode usar @Data para gerar automaticamente
}
