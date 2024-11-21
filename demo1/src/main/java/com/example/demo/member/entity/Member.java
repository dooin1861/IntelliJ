package com.example.demo.member.entity;

import com.example.demo.member.service.WrongIdPasswordException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data            // Getter Setter
@Builder        // DTO -> Entity화
@AllArgsConstructor    // 모든 컬럼 생성자 생성
@NoArgsConstructor    // 기본 생성자
@Table(name = "member")
public class Member {

    @Id    // 내가 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)	// 자동 id 생성
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String username;

    @Size(min = 4, message = "비밀번호는 최소 4자리 이상이어야 합니다.")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private LocalDateTime regdate;

    // 비밀번호 변경 메소드
    public void changePassword(String oldPassword, String newPassword, PasswordEncoder passwordEncoder) {
        // 최소 4자리 이상으로 지정
        if (newPassword.length() < 4) {
            throw new WrongIdPasswordException("비밀번호는 최소 4자리 이상이어야 합니다.");
        }

        if (!passwordEncoder.matches(oldPassword, this.password)) {
            throw new WrongIdPasswordException("기존 비밀번호가 틀렸습니다.");
        }

        // 새 비밀번호 암호화
        this.password = passwordEncoder.encode(newPassword);
    }

    // 비밀번호 비교 메소드
    public boolean matchPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }
}
