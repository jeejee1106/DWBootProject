package com.jeejee.webservice.web;

import com.jeejee.webservice.web.dto.PostsListResponseDto;
import com.jeejee.webservice.web.dto.PostsResponseDto;
import com.jeejee.webservice.web.dto.PostsSaveRequestDto;
import com.jeejee.webservice.service.PostsService;
import com.jeejee.webservice.web.dto.PostsUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PostsApiController {

    private PostsService postsService;

    //등록기능
    @PostMapping("/api/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){
        return postsService.save(dto);
    }

    //조회
    @GetMapping("api/posts/{id}") //Mapping의 Method가 달라서 URL이 다른 메서드랑 겹쳐도 상관 없다!
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    //수정
    @PutMapping("/api/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    //삭제
    @DeleteMapping("/api/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
