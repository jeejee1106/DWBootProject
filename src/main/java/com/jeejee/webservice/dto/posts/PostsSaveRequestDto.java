package com.jeejee.webservice.dto.posts;

import com.jeejee.webservice.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    //여기서 Entity 클래스와 거의 유사한 형태임에도 DTO 클래스를 추가로 생성했는데요.
    //절대로 테이블과 매핑되는 Entity 클래스를 Request/ Response 클래스로 사용해서는 안됩니다.
    //Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면 Request와 Response용 DTO는 View를 위한 클래스라 정말 자주 변경이 필요합니다.
    //꼭꼭 Entity 클래스와 Controller에서 쓸 DTO는 분리해서 사용하시길 바랍니다.

    private String title;
    private String content;
    private String author;

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
