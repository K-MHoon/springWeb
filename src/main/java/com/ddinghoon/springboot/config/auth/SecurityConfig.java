package com.ddinghoon.springboot.config.auth;

import com.ddinghoon.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// Spring Security 설정들을 활성화 시켜준다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console화면을 사용하기 위해 해당 옵션들을 disable 한다.
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                // URL별 권한 관리를 설정하는 옵션의 시작점
                // authorizeRequests가 선언되어야만 andMatchers 옵션을 사용할 수 있다.
                .authorizeRequests()
                // 권한 관리 대상을 지정하는 옵션
                // URL, HTTP 메소드별로 관리 가능
                // "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 준다.
                // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 설정.
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 설정된 값들 이외 나머지 URL
                // authenticated()를 추가하여, 나머지 URL들은 모두 인증된 사용자들에게만 허용되게 한다.
                .anyRequest().authenticated()
                .and()
                // 로그아웃 기능에 대한 여러 설정의 진입점
                // 로그아웃 성공 시 / 주소로 이동
                .logout().logoutSuccessUrl("/")
                .and()
                // OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                .oauth2Login()
                // 로그인 성공 후 사용자 정보를 가져올 때 설정들을 담당.
                .userInfoEndpoint()
                // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                // 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
                .userService(customOAuth2UserService);
    }
}
