package study.jdnc7.homeworkproject.jwt;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//유효한 자격증명을 제공하지 않고 접근하려 할때 401 Unauthorized 에러를 유발하는 클래스
//@Component
public class JwtAuthenticationEntryPoint{
//        implements AuthenticationEntryPoint { // 여기에서 인가와 인증을 구분해줘야함 근데 안쓸거
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//    }
}