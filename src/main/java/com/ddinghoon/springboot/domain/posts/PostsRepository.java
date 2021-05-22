package com.ddinghoon.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// DB Layer 접근자, Mybatis 등에서 Dao라고 불린다.
// JPA에선 Repository라고 부르며, 인터페이스로 생성한다.
// 기본적인 CRUD 메소드가 자동으로 생성된다.
// JpaRepository<Entity 클래스, PK타입>
public interface PostsRepository extends JpaRepository<Posts, Long> {

    //SpringDataJpa에서 제공하지 않는 메소드는 아래처럼 쿼리로 작성해도 된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
