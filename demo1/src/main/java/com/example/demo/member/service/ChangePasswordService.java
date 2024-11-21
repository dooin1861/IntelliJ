package com.example.demo.member.service;

import com.example.demo.member.dao.MemberDao;
import com.example.demo.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

	private final MemberDao memberDao;
	private final PasswordEncoder passwordEncoder;


	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		// Optional 사용시 값이 없으면 예외를 던짐
		Member member = memberDao.selectByEmail(email)
				.orElseThrow(() -> new MemberNotFoundException("해당 이메일을 가진 사용자가 없습니다."));


		// 비밀번호 변경
		member.changePassword(oldPwd, newPwd, passwordEncoder);
		memberDao.update(member);
	}

}
