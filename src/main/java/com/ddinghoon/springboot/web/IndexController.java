package com.ddinghoon.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤에 파일 확장자는 자동으로 지정
    // src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리하게 된다.
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
