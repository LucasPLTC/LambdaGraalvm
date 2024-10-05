package br.com.itau.app.service;

import br.com.itau.app.client.JsonPlaceholderClient;
import br.com.itau.app.dto.PostDTO;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final JsonPlaceholderClient jsonPlaceholderClient;

    public PostService(JsonPlaceholderClient jsonPlaceholderClient) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
    }

    public PostDTO getPost(Long id) {
        return jsonPlaceholderClient.getPostById(id);
    }
}
