package com.jeejee.webservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 추가, 기본생성자의 접근 권한을 protected로 제한. Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것은 막되, JPA에서 Entity 클래스를 생성하는것은 허용하기 위해 추가
@Getter
@Entity //테이블과 링크될 클래스임을 나타냄(언더스코어 네이밍으로 이름 매칭)
public class Posts extends BaseTimeEntity {

    @Id //해당 테이블의 pk필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk의 생성규칙을 나타냄 - MySQL의 auto_increament와 같은 기능
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
