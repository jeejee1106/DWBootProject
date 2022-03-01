package com.jeejee.webservice.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WebRestControllerDtoTest {

    @Test
    public void lombokTest() {
        //given
        String name = "Test";
        int amount = 1000;

        //when
        WebRestControllerDto dto = new WebRestControllerDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

        /**
         * assertThat() : 검증하고자 하는 대상을 매개변수로 받는다. 즉, 
         */
    }
}