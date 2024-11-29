package com.example.sb1128.controller;

import com.example.sb1128.member.dto.MemberDto;
import com.example.sb1128.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistController {
    private final MemberService memberService;

    public RegistController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // POST 메소드: 데이터 처리
    @PostMapping("/register")
    public String register(@ModelAttribute MemberDto memberDto, RedirectAttributes redirectAttributes) {
        try {
            memberService.register(memberDto);
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원가입 중 오류가 발생했습니다.");
            return "redirect:/register";
        }
    }
}
