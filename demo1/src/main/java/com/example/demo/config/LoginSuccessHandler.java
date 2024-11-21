package com.example.demo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 로그인 성공 시 로그 찍기
        String username = authentication.getName(); // 로그인한 사용자 이름 (username 또는 email 등)
        log.info("Login successful for user: {}", username); // 로그 찍기
        try {
            // 로그인 성공 후 리다이렉트 경로 설정
            response.sendRedirect("/"); // 루트 페이지로 리다이렉트
        } catch (Exception e) {
            log.error("Error during redirecting after login success", e);
        }
    }
}
