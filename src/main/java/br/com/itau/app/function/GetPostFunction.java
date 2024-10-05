package br.com.itau.app.function;

import br.com.itau.app.dto.PostDTO;
import br.com.itau.app.dto.RequestDTO;
import br.com.itau.app.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GetPostFunction {

    private final PostService postService;

    public GetPostFunction(PostService postService) {
        this.postService = postService;
    }

    @Bean
    public Function<RequestDTO, PostDTO> getPost() {
        return request -> postService.getPost(request.getId());
    }
}
