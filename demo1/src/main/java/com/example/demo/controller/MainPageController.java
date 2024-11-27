package com.example.demo.controller;

import com.example.demo.member.service.UserDetailsEmail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String main1(@AuthenticationPrincipal UserDetailsEmail userDetailsEmail, HttpSession session) {
        if (userDetailsEmail != null) {
            session.setAttribute("username", userDetailsEmail.getUsername());
            session.setAttribute("email", userDetailsEmail.getEmail());
            String role = userDetailsEmail.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                    .collect(Collectors.joining(", "));
            session.setAttribute("role", role);
        } else {
            session.setAttribute("username", "Guest");
//            session.setAttribute("email", "");
            session.setAttribute("role", "Guest");
        }
        return "main/main"; // 템플릿 이름
    }

    @GetMapping("/sample/login")
    public String login() {
        return "sample/login"; // login.html 템플릿을 반환
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/"; // 로그아웃 후 메인 페이지로 리다이렉트
    }

    @GetMapping("/sample/accessDenied")
    public String accessDenied() {
        return "sample/accessDenied"; // 접근 거부 페이지
    }

    @GetMapping("/main/main")
    public String main() {
        return "main/main";
    }


    @GetMapping("/profile/myPage")
    public String myPage() {
        return "profile/myPage";  // 마이 페이지로 이동
    }
}
