package com.example.demo.member.dao;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberDao {

	private final MemberRepository memberRepository;


	public Optional<Member> selectByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	public void insert(Member member) {
		memberRepository.save(member);
	}

	public void update(Member member) {
		memberRepository.save(member);
	}

	public List<Member> selectAll() {
		return memberRepository.findAll();
	}

}
