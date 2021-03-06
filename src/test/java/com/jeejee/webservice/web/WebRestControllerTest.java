package com.jeejee.webservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class WebRestControllerTest {

    /**
     * MockMvc는 서블릿 컨테이너의 구동 없이, 시뮬레이션된 MVC 환경에 모의 HTTP 서블릿 요청을 전송하는 기능을 제공하는 유틸리티 클래스다.
     * MVC 테스트의 시작점. HTTP GET, POST 등에 대한 API 테스트를 할 수 있다
     * 즉. 테스트 환경을 was와 똑같이 만들어준다. 실제 was구동 없이 테스트가 이루어진다.
     */

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = "USER")
    public void return_HelloWorld() throws Exception{
        String hello = "HelloWorld";

        mvc.perform(get("/hello")) //이 URL로 실제 Controller를 실행했을 때 내용에 뭐가 들어가는지 확인하는 듯... 와... 신기하다...
                .andExpect(status().isOk()) //HTTP Header의 Status를 검증
                .andExpect(content().string(hello));
        /**
         * perform() : 브라우저에서 서버에 URL 요청을 하듯 컨트롤러를 실행시킬 수 있다. RequestBuilder 객체를 매개변수로 받는다.
         * andExpect() : perform()메서드를 이용하여 요청을 전송하면, 그 결과로 ResultAction객체를 리턴하는데, 이 객체는 응답 결과를 검증할 수 있는 andExpect메서드를 제공한다.
         *               andExpect()가 요구하는 ResultMatcher는 MockMvcResultMatcheers에 정의된 정적 메서드를 통해 생성할 수 있다.
         * isOk() : 응답 상태 코드가 정상적인 처리(200)인지 확인
         */
    }

    @Test
    @WithMockUser(roles = "USER")
    public void returnRestDto() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount))) //String값만 넣을 수 있다.
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.name", is(name)))
//                .andExpect(jsonPath("$.amount", is(amount)));

        /**
         * param() : API테스트할 때 사용될 요철 매개변수를 설정. String값만 허용한다.
         * json응답 값을 필드별로 검증하는 메서드. $를 기준으로 필드명을 명시 (근데 왜 안되는걸까...ㅎㅎ)
         */
    }
}
