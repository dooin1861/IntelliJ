package com.example.sb1030.spring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

	@Id    // 내가 PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 자동 id 생성
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;

	@Column(name = "REGISTER_DATETIME")
	private LocalDateTime registerDateTime;

	public Member(String email, String password, 
			String name, LocalDateTime registerDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = "USER";
		this.registerDateTime = registerDateTime;
	}

	void setId(Long id) {
		this.id = id;
	}

	public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword))
			throw new WrongIdPasswordException();
		this.password = newPassword;
	}
	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}

}
