package com.jeejee.webservice.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //선언된 모든 필드의 getter메서드 생성
@RequiredArgsConstructor //선언된 필드 중 final 키워드가 포함된 필드의 생성자 생성 (finel이 없는 필드는 포함 안됨)
public class WebRestResponseDto {
    private final String name;
    private final int amount;
}
