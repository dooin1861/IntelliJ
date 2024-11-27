package com.example.sb1127.tab.repository;

import com.example.sb1127.tab.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    // 기본 CRUD 메서드는 JpaRepository에서 제공
    // 필요한 경우 커스텀 쿼리 메서드 추가
}