package com.example.demo.member.service;

import com.example.demo.member.dao.MemberDao;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("==========>사용자: " + username);
//        UserDetails user = User.withUsername("user123")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//        return user;

//        Member member = Member.builder()
//                .id(1001L)
//                .username("홍길동")
//                .password(passwordEncoder().encode("1234"))
//                .email("hong@aaa.com")
//                .build();
//        return toUserDetails(member);

        Optional<Member> member = memberRepository.findByEmail(username);
        if (!member.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return toUserDetailsEmail(member.get());

    }
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetails toUserDetailsEmail(Member member) {
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserDetailsEmail(
                member.getUsername(),
                member.getPassword(),
                member.getEmail(), // 이메일을 추가
                authorities // 권한 정보 (필요한 경우)
        );
    }
}
