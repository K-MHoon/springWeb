package com.ddinghoon.springboot.service.posts;

import com.ddinghoon.springboot.domain.posts.Posts;
import com.ddinghoon.springboot.domain.posts.PostsRepository;
import com.ddinghoon.springboot.web.dto.PostsListResponseDto;
import com.ddinghoon.springboot.web.dto.PostsResponseDto;
import com.ddinghoon.springboot.web.dto.PostsSaveRequestDto;
import com.ddinghoon.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신해준다.
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        // deleteById 메소드를 이용하면 id로 삭제할 수도 있다.
        // 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제한다.
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    // readOnly = true를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선된다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                // map(posts -> new PostsListResponseDto(posts))
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
