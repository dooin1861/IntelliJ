package com.example.sb1128.member.repository;

import com.example.sb1128.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 사용자 이름으로 회원 찾기
    Optional<MemberEntity> findByUsername(String username);

    // 닉네임으로 회원 찾기
    Optional<MemberEntity> findByNickname(String nickname);

    // 사용자 이름 존재 여부 확인
    boolean existsByUsername(String username);

    // 닉네임 존재 여부 확인
    boolean existsByNickname(String nickname);

    // 사용자 이름이나 닉네임으로 회원 찾기
    Optional<MemberEntity> findByUsernameOrNickname(String username, String nickname);
}