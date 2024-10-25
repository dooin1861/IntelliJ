package com.example.sb1022secu4.config;

import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("--------filterChain--------");
        http.authorizeHttpRequests()              // 이 코드는 예외처리를 해야 사용 가능
//                .antMatchers("/**").denyAll()   // 어떤 요청이든 불허
//                .antMatchers("/**").permitAll() // 어떤 요청이든 허가
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/sample/all").permitAll()
//                .antMatchers("/sample/register").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/sample/admin").hasRole("ADMIN") // 권한이 ADMIN인 유저만 허용, 인증은 됐으나 인가가 안됨
                .anyRequest().authenticated();    // 요청을 처리하기 위해 사용자의 인증 요구
        http.formLogin();       // 기본 로그인 폼
        http.csrf().disable();
        http.logout();
        http.exceptionHandling().accessDeniedPage("/sample/accessDenied"); // 접근 권한이 없는 페이지에 접근할 때 accessDenied로
        http.csrf()
                .ignoringAntMatchers("/h2-console/**")
//                .ignoringAntMatchers("/sample/**")
                .and().headers().frameOptions().sameOrigin();
        return http.build();
    }
}
