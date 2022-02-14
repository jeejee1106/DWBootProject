package com.jeejee.webservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class) //JUnit 5부터 사용 불가
//@ExtendWith(SpringExtension.class) //@RunWith대신 @ExtendWith를 쓸 수 있는데, 사실 얘도 생략가능하다.
@SpringBootTest //왜냐하면 @SpringBootTest가 이미 그 코드를 가지고 있기 때문이다!
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        /*
        * 이후 테스트 코드에 영향을 끼치지 않기 위해
        * 테스트 메소드가 끝날 때마다 repository 전체를 비우는 코드
        * */
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("test@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle().equals("테스트 게시글"));
        assertThat(posts.getContent().equals("테스트 본문"));
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시물")
                .content("테스크 본문")
                .author("test@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreateDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}
