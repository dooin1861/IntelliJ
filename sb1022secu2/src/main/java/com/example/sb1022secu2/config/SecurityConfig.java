package com.example.sb1022secu2.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Log4j2
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("--------filterChain--------");
        http.authorizeHttpRequests()              // 예외처리를 해야함
//                .antMatchers("/**").denyAll()   // 어떤 요청이든 다 불허
//                .antMatchers("/**").permitAll() // 어떤 요청이든 다 허가
                .anyRequest().authenticated();    // 요청을 처리하기 위해 사용자의 인증 요구
        http.formLogin();
        return http.build();
    }
}
