package com.jeejee.webservice.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

/**
 * 보통 ibatis/MyBatis 등에서 Dao라고 불리는 DB Layer 접근자이다.
 * JPA에선 Repository라고 부르며 인터페이스로 생성한다.
 * 단순히 인터페이스를 생성후, JpaRepository<Entity클래스, PK타입>를 상속하면 <<<!!!! 기본적인 CRUD 메소드가 자동생성 !!!!!>>> 된다.
 * 특별히 @Repository를 추가할 필요도 없다.
 *
 * 반드시 Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
 */

public interface PostsRepository extends JpaRepository<Posts, Long> {

    /**
     * 실제로 아래 코드는 SpringDataJpa에서 제공하는 기본 메소드만으로 해결할 수 있다.
     * 굳이 @Query를 쓴 이유는, SpringDataJpa에서 제공하지 않는 메소드는 이처럼 쿼리로 작성해도 된다는 것을 보여주기 위함!
     * 또한 가독성도 높아짐!
     *
     * 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건등으로 인해 이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용.
     * 대표적 예로 querydsl, jooq, MyBatis 등이 있다.
     */

    @Query("select p from Posts p order by p.id desc")
    Stream<Posts> findAllDesc();
}
