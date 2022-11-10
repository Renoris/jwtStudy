package study.jdnc7.homeworkproject.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig2 {
    //extends WebSecurityConfigurerAdapte
//
//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity
//                .ignoring().antMatchers("/h2-console/**","/favicon.ico");
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/api/login").permitAll()
//                .anyRequest().authenticated();
//    }

}
