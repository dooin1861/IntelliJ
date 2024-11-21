package com.example.demo.member.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsEmail extends User {
    private String email;

    public UserDetailsEmail(String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)  // 권한을 문자열로 변환
                .collect(Collectors.joining(", "));  // 쉼표로 구분된 권한 목록 반환
    }

}
