package com.jeejee.webservice.dto.posts;

import com.jeejee.webservice.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    /**
     * 여기서 Entity 클래스와 거의 유사한 형태임에도 DTO 클래스를 추가로 생성했는데,
     * 절대로 테이블과 매핑되는 Entity 클래스를 Request/ Response 클래스로 사용해서는 안된다.
     * Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면, Request와 Response용 DTO는 View를 위한 클래스라 정말 자주 변경이 필요하다.
     * View Layer와 DB Layer를 철저하게 역할 분리를 하는게 좋다.
     * 꼭꼭 Entity 클래스와 Controller에서 쓸 DTO는 분리해서 사용하시길!!!!!
     */

    /**
     * Entity클래스에는 없는 setter가???
     * Controller에서 @RequestBody로 외부에서 데이터를 받는 경우엔 기본생성자 + set메소드를 통해서만 값이 할당된다.
     * 그래서 이때만 setter를 허용한다!
     */

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
