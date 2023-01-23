package com.jeejee.webservice.service;

import com.jeejee.webservice.domain.post.Posts;
import com.jeejee.webservice.domain.post.PostsRepository;
import com.jeejee.webservice.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void dtoDataIsSavePostsTable() {
        //given - Dto 클래스가
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("test3@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();

        //when - service.save 메소드에 전달되면,
        postsService.save(dto);

        //then -  DB에 잘 저장되었는지 검증
        Posts posts = postsRepository.findAll().get(2);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
}
