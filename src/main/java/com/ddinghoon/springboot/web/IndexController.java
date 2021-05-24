package com.ddinghoon.springboot.web;

import com.ddinghoon.springboot.config.auth.LoginUser;
import com.ddinghoon.springboot.config.auth.dto.SessionUser;
import com.ddinghoon.springboot.service.posts.PostsService;
import com.ddinghoon.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤에 파일 확장자는 자동으로 지정
    // src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리하게 된다.
    // Model은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
    @GetMapping("/")
    // 기존에 httpSession.getAttribute("user")로 가져온 세선 정보 값 개선
    // 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있음.
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // 세션에 저장된 값이 있을 때만 model에 userName으로 등록한다.
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
