package study.jdnc7.homeworkproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import study.jdnc7.homeworkproject.jwt.JwtFilter;
import study.jdnc7.homeworkproject.jwt.TokenProvider;

//TokenProvider와 JwtFilter를 SecurityConfig에 적용할때 사용할 config
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity httpSecurity) {
        //jwtFilter를 security로직에 등록하는 과정
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
