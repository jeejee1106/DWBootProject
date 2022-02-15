package com.jeejee.webservice.web;

import com.jeejee.webservice.domain.PostsRepository;
import com.jeejee.webservice.dto.posts.PostsSaveRequestDto;
import com.jeejee.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor //모든 필드를 인자값으로 하는 생성자를 생성
public class WebRestController {

    private PostsRepository postsRepository;
    private PostsService postsService;

    /**
     * 눈에 보이지는 않지만 롬복의 @AllArgsConstructor로 인해 현재 기본 생성자가 만들어진 상태다.
     * 생성자가 하나일 땐 @Autowired의 생략이 가능하다.
     * 즉, 현재 기본 생성자에 의존성 주입이 되어있는 상태이다!!
     */

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public Long savePost(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }
}
