package com.jeejee.webservice.config.auth;

import com.jeejee.webservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화시켜 준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "pofile")
                .permitAll()
                .antMatchers("/api/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}

/**
 * 18: h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
 * 21: URL별 권한 관리를 설정하는 옵션의 시작점으로, authorizeRequests가 선언되어야지만 antMatchers 옵션을 사용할 수 있다.
 * 22: antMatchers. 권한 관리 대상을 지정하는 옵션으로, URL, HTTP 메소드별로 관리가 가능하다.
 * 위 코드에서는 "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 주었다.
 * "api/** 주소를 가진 API는 USER 권한을 가진 사람만 사용하도록 하였다.
 * 25: anyRequest. 설정된 값들 이외의 나머지 URL들을 나타낸다.
 * 여기서는 authenticated()를 추가하여, 나머지 URL들은 모두 인증된 사용자 (로그인한)에게만 허용하도록 한다.
 * 28: 로그아웃 기능에 대한 여러 설정의 진입점으로, 성공 시 / 주소로 이동한다.
 * 30: OAuth2 로그인 기능에 대한 여러 설정의 진입점이다.
 * 31: OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당한다.
 * 32: userService. 로그인 성공 후 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다. 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
 */