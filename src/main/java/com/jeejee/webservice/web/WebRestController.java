package com.jeejee.webservice.web;

import com.jeejee.webservice.domain.PostsRepository;
import com.jeejee.webservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor //모든 필드를 인자값으로 하는 생성자를 생성
public class WebRestController {

    private PostsRepository postsRepository;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public void savePost(@RequestBody PostsSaveRequestDto dto) {
        postsRepository.save(dto.toEntity());
    }
}
