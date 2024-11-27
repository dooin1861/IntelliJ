package com.example.sb1127.tab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "score/create";  // 메인 페이지로 접속하면 바로 악보 생성 페이지로 이동
    }
}