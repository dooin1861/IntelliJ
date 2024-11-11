package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BeginController {

    @GetMapping("/")
    public String main1(@AuthenticationPrincipal User user, HttpSession session) {
        if (user != null) {
            session.setAttribute("username", user.getUsername()); // 로그인된 사용자의 이름을 모델에 추가
        } else {
            session.setAttribute("username", "Guest"); // 로그인되지 않은 경우 null로 설정
        }
        return "main/main"; // 템플릿 이름
    }

    @GetMapping("/main/main2")
    public String main2(@AuthenticationPrincipal User user, HttpSession session) {
        if (user != null) {
            session.setAttribute("username", user.getUsername()); // 로그인된 사용자의 이름을 모델에 추가
        } else {
            session.setAttribute("username", "Guest"); // 로그인되지 않은 경우 null로 설정
        }
        return "main/main2"; // 템플릿 이름
    }


    @GetMapping("/info/staff")
    public void info() {

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

}
