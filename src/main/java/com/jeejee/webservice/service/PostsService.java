package com.jeejee.webservice.service;

import com.jeejee.webservice.domain.PostsRepository;
import com.jeejee.webservice.dto.PostsMainResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller에서 Dto.toEntity를 통해서 바로 전달해도 되는데 굳이 Service에서 Dto를 받는 이유는 간단하다.
 * Controller와 Service 의 역할을 분리하기 위해서다.
 * 비지니스 로직 & 트랜잭션 관리는 모두 Service에서 관리하고, View 와 연동되는 부분은 Controller에서 담당하도록 구성한다.
 */

@Service
@AllArgsConstructor
public class PostsService {
    private PostsRepository postsRepository;

    //일반적으로 DB 데이터를 등록/수정/삭제 하는 Service의 메소드는 @Transactional를 필수적으로 가져간다.
    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }
}
