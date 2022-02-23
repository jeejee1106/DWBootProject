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
        //given - 테스트 기반 환경을 구축하는 단계(여기선 @Builder의 사용법도 같이 확인)
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("test@gmail.com")
                .build());

        //when - 테스트하고자 하는 행위 선언 (여기선 Posts가 DB에 insert되는 것을 확인하기 위함)
        List<Posts> postsList = postsRepository.findAll();

        //then - 실제로 DB에 insert되었는지 확인하기 위해 조회 후 입력된 값 확인
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle().equals("테스트 게시글"));
        assertThat(posts.getContent().equals("테스트 본문"));

        /**
         * DB가 설치가 안되어있는데 Repository를 사용할 수 있는 이유는, SpringBoot에서의 테스트 코드는 메모리 DB인 H2를 기본적으로 사용하기 때문
         * 테스트 코드를 실행하는 시점에 H2 DB를 실행시킨다.
         * 테스트가 끝나면 H2 DB도 같이 종료된다.
         */
    }

    @Test
    public void BaseTimeEntity_등록() {
        //Posts를 저장한뒤, 해당 Posts에 createdDate와 modifiedDate이 있는지 확인해보는 테스트

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
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}
