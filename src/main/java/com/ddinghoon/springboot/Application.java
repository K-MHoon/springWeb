package com.ddinghoon.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//메인 클래스
// 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
// 항상 최상단에 위치해야 함.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
