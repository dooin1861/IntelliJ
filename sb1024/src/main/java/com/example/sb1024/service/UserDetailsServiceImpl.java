package com.example.sb1024.service;

import com.example.sb1024.entity.Member;
import com.example.sb1024.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 유저정보를 이 코드가 받음
        log.info("==============> 사용자: " + username);
        if (username.equals("user")) {
            UserDetails user = User.withUsername("user")
                    .password(passwordEncoder.encode("1234")) // 필드로 접근
                    .roles("ADMIN")
                    .build();
            return user;
        }
//        Member member = Member.builder() // 백도어.
//                .id(1001L)
//                .username("user")
//                .password(passwordEncoder().encode("1234"))
//                .email("user@user.com")
//                .build();
//        return toUserDetails(member);

        Optional<Member> member = memberRepository.findByUsername(username);
       if (!member.isPresent()) {
           System.out.println("로그인 실패");
           throw new UsernameNotFoundException(username);
        }
        return toUserDetails(member.get());
    }

    private UserDetails toUserDetails(Member member) {
        return User.builder()
                .username(member.getUsername())
//                .username(member.getEmail())
                .password(member.getPassword())
//                .authorities(new SimpleGrantedAuthority(member.getRole().toString()))
                .roles(member.getRole())
                .build();
    }

    public List<Member> findAllUsers() {
        return memberRepository.findAll(); // 모든 사용자 반환
    }

}
