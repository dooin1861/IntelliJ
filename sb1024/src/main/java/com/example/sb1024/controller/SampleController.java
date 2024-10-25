package com.example.sb1024.controller;

import com.example.sb1024.entity.Member;
import com.example.sb1024.service.BoardServiceImpl;
import com.example.sb1024.service.UserDetailsServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//@RequestMapping("/sample/") // 기본 경로를 sample로 지정
@Log4j2
public class SampleController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final BoardServiceImpl boardServiceImpl;

    public SampleController(UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder, BoardServiceImpl boardServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.boardServiceImpl = boardServiceImpl;
    }

    @GetMapping("/sample/accessDenied")
    public void exAccessDenied() {}

    @GetMapping("/sample/all")
    public void exAll() {
        log.info("exAll...........");
    }

    @GetMapping("/sample/admin")
    public void exAdmin(Model model) {
        log.info("exAdmin...........");
        List<Member> members = userDetailsServiceImpl.findAllUsers(); // 모든 사용자 조회
        model.addAttribute("members", members); // 모델에 사용자 리스트 추가
    }

    @GetMapping("/sample/member")
    public void exMember() {
        log.info("exMember............");
    }

    @GetMapping("/sample/step1")
    public void exStep1(Model model) {
        // 새로운 Member 객체를 초기화하여 모델에 추가
        model.addAttribute("member", new Member());
        log.info("exStep1............");
    }

    @PostMapping("/sample/step1")
    public void handleStep1(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("member", member);
        log.info("handleStep1: {}", member);
    }

    @GetMapping("/sample/step2")
    public void exStep2(@ModelAttribute("member") Member member, Model model) {
        log.info("exStep2: {}", member);
        model.addAttribute("member", member); // 모델에 member 추가
    }

    @PostMapping("/sample/step2")
    public void handleStep2(@ModelAttribute Member member) {
        userDetailsServiceImpl.saveMember(member);
        member.setRole("USER");
        log.info("handleStep2: {}", member);

        // 추가적인 처리 로직이 필요하다면 여기에 추가
    }


}
