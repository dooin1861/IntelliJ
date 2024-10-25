package com.example.sb1024.controller;

import com.example.sb1024.entity.Member;
import com.example.sb1024.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
public class BeginController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/")
    public String index() {
        return "/sample/all";
    }

//    @PostConstruct
//    public void init() {
//        Member member = Member.builder()
//                .id(1001L)
//                .username("hong1")
//                .password(passwordEncoder().encode("1234"))
//                .confirmPassword("1234")
//                .email("hong1@aaa.com")
//                .role("ADMIN")
//                .build();
//
//        memberRepository.save(member);
//
//        member = Member.builder()
//                .id(1002L)
//                .username("test1")
//                .password(passwordEncoder().encode("1234"))
//                .confirmPassword("1234")
//                .email("test1@aaa.com")
//                .role("USER")
//                .build();
//        memberRepository.save(member);
//    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

