package com.example.sb1021_2.spring;

import com.example.sb1021_2.entity.Member;
import com.example.sb1021_2.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberRegisterService {
	private final MemberRepository memberRepository;
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao, MemberRepository memberRepository) {
		this.memberDao = memberDao;
		this.memberRepository = memberRepository;
	}

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}

	public List<Member> findAllMembers() {
		return memberRepository.findAll();
	}
}
