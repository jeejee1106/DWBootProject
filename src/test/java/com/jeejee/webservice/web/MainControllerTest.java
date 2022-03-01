package com.jeejee.webservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void mainPageLoading() {

        //URL 호출 시 페이지의 내용을 제대로 호출하는지 확인하는 테스트
        //즉, "/"로 호출했을 때 main.mustache에 포함된 코드들이 있는지 확인하는 것이다.

        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("Start WebService with SpringBoot");
    }
}
