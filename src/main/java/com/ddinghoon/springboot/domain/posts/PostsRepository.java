package com.ddinghoon.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// DB Layer 접근자, Mybatis 등에서 Dao라고 불린다.
// JPA에선 Repository라고 부르며, 인터페이스로 생성한다.
// 기본적인 CRUD 메소드가 자동으로 생성된다.
// JpaRepository<Entity 클래스, PK타입>
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
