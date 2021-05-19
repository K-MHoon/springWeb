package com.ddinghoon.springboot.web;

import com.ddinghoon.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 컨트롤러를 JSON으로 반환하는 컨트롤러로 만들어 준다.
// @ResponseBody를 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다.
@RestController
public class HelloController {

    //RequestMapping(method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    // 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션.
    // 외부에서 넘긴 파라미터를 자동으로 매칭해줌.
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
