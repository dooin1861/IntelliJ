package com.example.demo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
// @EnableGlobalMethodSecurity(    // Method 단위 시큐리티 처리.
//        prePostEnabled = true,  // Spring Security의 @PreAuthorize, @PreFilter /@PostAuthorize, @PostFilter어노테이션 활성화 여부
//        securedEnabled = true,  // @Secured어노테이션 활성화 여부
//        jsr250Enabled = true)   // @RoleAllowed 어노테이션 사용 활성화 여부
@Log4j2
public class SecurityConfig {

    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user1")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("---------filterChain-------------");
        http.authorizeHttpRequests()
//                .antMatchers("/**").denyAll()
//                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/sample/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/notice/**").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/artists/**").permitAll()
                .antMatchers("/dealers/**").permitAll()
                .antMatchers("/fragments/**").permitAll()
                .antMatchers("/inside/**").permitAll()
                .antMatchers("/support/**").permitAll()
                .antMatchers("/main/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/board/openBoardList.do").permitAll()
                .antMatchers("/board/openBoardDetail.do").permitAll()
                .antMatchers("/board/updateBoard.do").authenticated()
                .antMatchers("/board/deleteBoard.do").authenticated()
                .antMatchers("/main/myPage").permitAll()

                .anyRequest().authenticated();

        http.csrf()
                .ignoringAntMatchers("/board/**/comments");

        http.formLogin()
                .loginPage("/sample/login") // 로그인 페이지 URL 설정
                .loginProcessingUrl("/login") // 로그인 처리를 위한 URL
                .defaultSuccessUrl("/", true) // 로그인 성공 후 리다이렉트 URL 설정
                .permitAll()
                .and()

                .logout()
                .logoutUrl("/sample/logout") // 로그아웃 URL 설정
                .logoutSuccessUrl("/") // 로그아웃 성공 후 리다이렉트 URL 설정
                .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
                .deleteCookies("JSESSIONID") // 로그아웃 시 쿠키 삭제
                .permitAll();

        http.exceptionHandling().accessDeniedPage("/sample/accessDenied");



        return http.build();


    }
}
