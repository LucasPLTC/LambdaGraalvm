package br.com.itau.app.controller;

import br.com.itau.app.dto.PostDTO;
import br.com.itau.app.dto.RequestDTO;
import br.com.itau.app.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostDTO getPost(@RequestBody RequestDTO request) {
        return postService.getPost(request.getId());
    }
}
