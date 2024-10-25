package com.example.sb1021.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt 알고리즘으로 비밀번호 암호화하는 PasswordEncoder 생성
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user1") // 사용자의 정보를 저장 (user1, 1234, USER)
                .password(passwordEncoder().encode("1234"))
                .roles("USER")                       // USER라는 역할 설정 (user, admin, moderator 등등)
                .build();
        return new InMemoryUserDetailsManager(user); // 생성한 사용자 정보를 메모리에 저장하는 UserDetailsManager를 반환
    } // 백도어 같은 개념, 사용자를 늘리지는 말자

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()                      // http 보안 설정하기
                .antMatchers("/css/**").permitAll()     // css/** 경로로 누구나 접근 가능하게 허용
                .antMatchers("/js/**").permitAll()
                .anyRequest().authenticated();            // 이 외의 요청은 인증이 필요하게끔 설정
        http.formLogin();                                // 폼을 기반한 로그인 기능을 활성화
        http.csrf().disable();                          // 보호를 비활성화
        return http.build();
    }
}
