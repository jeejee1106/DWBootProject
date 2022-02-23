package com.jeejee.webservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 이 클래스는 실제 DB테이블과 매칭될 클래스이며 Entity클래스라고 한다. (@Entity 어노테이션 사용하면 테이블과 자바 클래스가 매핑된다.)
 * JPA를 사용하면 DB데이터에 작업할 경우 실제 쿼리를 날리기 보다는, 이 Entity클래스의 수정을 통해 작업한다.
 *
 * 즉, JPA는 자동으로 테이블을 생성하는 기능이 있다!!!
 *
 * JPA에서 제공하는 어노테이션
 * @Entity : 테이블과 링크 될 클래스임을 나타낸다. 언더스코어 네이밍으로 이름을 매칭 ex) ex) TestPosts.java -> test_posts table
 * @Id : 해당 테이블의 PK필드를 나타낸다.
 * @GeneratedValue : PK의 생성 규칙을 나타낸다. 기본값은 AUTO. (MySql의 auto_increment와 같음)
 * @Column : 테이블의 컬럼을 나타낸다. 굳이 추가하지 않아도 컬럼으로 지정이 되는데, 사용을 하는 경우는 기본값 외에 추강로 변경이 필요한 옵션이 있을 경우에만 사용한다.
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 추가, 기본생성자의 접근 권한을 protected로 제한. Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것은 막되, JPA에서 Entity 클래스를 생성하는것은 허용하기 위해 추가
@Getter
@Entity //테이블과 링크될 클래스임을 나타냄(언더스코어 네이밍으로 이름 매칭)
public class Posts extends BaseTimeEntity {

    @Id //해당 테이블의 pk필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //스프링부트 2.0이상에선 옵션 추가해야함(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false) //문자열의 경우 기본값이 VARCHAR(255)인데, 사이즈를 500으로 늘림
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //문자열의 경우 기본값이 VARCHAR(255)인데, 타입을 TEXT로 바꿈 (TEXT타입은 max사이즈가 무조건 65535이다.)
    private String content;

    private String author;

    @Builder //롬복 어노테이션 - 클래스에 선언하면 해당 클래스의 빌더패턴 클래스를 생성하고, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    /**
     * 빌더 패턴의 장점
     * 1. 필요한 데이터만 설정할 수 있음
     * 2. 유연성을 확보할 수 있음
     * 3. 가독성을 높일 수 있음
     * 4. 불변성을 확보할 수 있음
     * 참고 블로그 : https://mangkyu.tistory.com/163
     */
    }
}

/**
 * Entity 클래스를 생성할 때는 무분별한 setter메서드 생성을 주의해야한다.
 * settet는 인스턴스 값들이 언제 어디서 변해야하는지 코드상으로 명확히 구분할 수가 없다. (차후 기능 변경 시 복잡해진다.)
 * 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메서드를 추가해야한다!
 */