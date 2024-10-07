package br.com.itau.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
public class RequestDTO {
    @JsonProperty("id")
    private Long id;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
