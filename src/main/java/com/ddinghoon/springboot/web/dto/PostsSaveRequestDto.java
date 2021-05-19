package com.ddinghoon.springboot.web.dto;

import com.ddinghoon.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// Entity 클래스와 거의 유사한 형태이다.
// 하지만, 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
// Request와 Response용 Dto는 View를 위한 클래스라 자주 변경이 필요.
public class PostsSaveRequestDto {
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
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
