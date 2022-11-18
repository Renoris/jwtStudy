package study.jdnc7.homeworkproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import study.jdnc7.homeworkproject.jwt.JwtAccessDeniedHandler;
import study.jdnc7.homeworkproject.jwt.JwtAuthenticationEntryPoint;
import study.jdnc7.homeworkproject.jwt.TokenProvider;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //preAuthorize 를 메소드단위로 허용
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final String[] permitAllUrls = {"/api/login", "/auth/authenticate", "/user/signup"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring().antMatchers("/h2-console/**", "/favicon.ico");
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return setCustomConfiguration(httpSecurity).build();
    }

    private HttpSecurity setCustomConfiguration(HttpSecurity httpSecurity) throws Exception {
        setCsrfDisable(httpSecurity);

        setForH2Setting(httpSecurity); //h2 콘솔을 위한 설정

        setCreationPolicyStateLess(httpSecurity); //세션을 사용하지 않기때문에 세션을 stateless로 설정

        setJwtSecurityConfig(httpSecurity);

        setAuthorizeRequestUrl(httpSecurity); // url 셋팅

        setExceptionHandling(httpSecurity);

        return httpSecurity;
    }

    private void setAuthorizeRequestUrl (HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
                = httpSecurity.authorizeRequests();

        Arrays.stream(permitAllUrls).forEach((item) -> registry.antMatchers(item).permitAll());

        registry.anyRequest().authenticated();
    }

    private void setForH2Setting(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().frameOptions().sameOrigin();
    }
    private void setCsrfDisable(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
    }
    private void setCreationPolicyStateLess(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    private void setJwtSecurityConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.apply(new JwtSecurityConfig(tokenProvider));
    }

    private void setExceptionHandling(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);

    }
}
