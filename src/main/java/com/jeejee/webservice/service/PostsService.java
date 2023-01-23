package com.jeejee.webservice.service;

import com.jeejee.webservice.domain.post.Posts;
import com.jeejee.webservice.domain.post.PostsRepository;
import com.jeejee.webservice.web.dto.PostsListResponseDto;
import com.jeejee.webservice.web.dto.PostsResponseDto;
import com.jeejee.webservice.web.dto.PostsSaveRequestDto;
import com.jeejee.webservice.web.dto.PostsUpdateRequestDto;
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
    //@Transactional은 메소드 실행 중 Exception이 발생하면 해당 메소드에서 실행 중 이루어진 모든 DB작업을 초기화 시킨다.

    //등록기능
    @Transactional
    public Long save(PostsSaveRequestDto dto){ //Service 메소드는 Entity를 바로 받지 않고, 이전 포스팅 에서 생성한 Save용 DTO인 PostsSaveRequestDto를 받아서 저장
        return postsRepository.save(dto.toEntity()).getId(); //호출한쪽에서 저장한 게시글의 id를 알수있도록 id반환
        //save는 jpaRepository에서 자동 생성 해주는 등록 메서드
    }

    //전체 글 조회
    @Transactional(readOnly = true) //readOnly = true 옵션은 트랜잭션 범위는 유지하되, 조회 기능만 남겨두는 역할을 한다. 조회 속도 개선!
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                //repository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsMainResponseDto로 변환 -> List로 반환하는 메서드
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 수정/조회 기능
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    //글 삭제
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
