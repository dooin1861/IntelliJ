package com.example.sb1202_a._controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class _MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main/mainPage";
    }
}
