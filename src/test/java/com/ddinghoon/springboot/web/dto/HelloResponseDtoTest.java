package com.ddinghoon.springboot.web.dto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        //assertThat 테스트 검증 라이브러리의 검증 메서드
        //검증하고 싶은 대상을 인자로 받는다.
        //메소드 체이닝이 지원되어, 메소드를 이어서 사용할 수 있다.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}