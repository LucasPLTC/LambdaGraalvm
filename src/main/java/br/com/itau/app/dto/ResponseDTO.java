package br.com.itau.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseDTO {

    @JsonProperty("resultado")
    private String resultado;

    @JsonProperty("post")
    private PostDTO post;

    // Getters e Setters

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
