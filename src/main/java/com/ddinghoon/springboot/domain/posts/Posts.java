package com.ddinghoon.springboot.domain.posts;

import com.ddinghoon.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Lombok 어노테이션
@Getter
// 기본 생성자 자동 추가
// public Posts() {} 와 동일한 효과
@NoArgsConstructor
// JPA 어노테이션
// 테이블과 링크될 클래스임을 나타낸다.
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 매칭한다.
// Entity 클래스에서는 절대 Setter 메소드를 만들지 않습니다.
@Entity
public class Posts extends BaseTimeEntity {

    // 해당 테이블의 PK 필드 표시
    @Id
    // PK의 생성 규칙을 나타낸다.
    // 스프링부트 2.0 에서 GenerationType.IDENTITY 옵션을 추가해야 auto_increment가 된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블 컬럼 표시.
    // 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 된다.
    // 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
    // ex) VARCHAR(255)가 기본 값인데, 500으로 늘리고 싶을 경우
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
