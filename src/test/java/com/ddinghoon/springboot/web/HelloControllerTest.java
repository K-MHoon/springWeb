package com.ddinghoon.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행
//SpringRunner라는 스프링 실행자를 사용
//즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@RunWith(SpringRunner.class)
//Web(Spring MVC)에 집중할 수 있는 어노테이션
//선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
//단, @Service, @Component, @Repository 등은 사용할 수 없다.
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    // 웹 API를 테스트할 때 사용
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
        mvc.perform(get("/hello"))
                //mvc.perform의 결과를 검증한다.
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
        // param은 API 테스트할 때 사용될 요청 파라미터 설정, 단 값은 String만 가능
        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                //mvc.perform의 결과를 검증한다.
                .andExpect(status().isOk())
                //JSON 응답값을 필드별로 검증할 수 있는 메소드.
                //$를 기준으로 필드명을 명시한다.
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }


}