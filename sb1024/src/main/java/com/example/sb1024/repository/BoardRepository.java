package com.example.sb1024.repository;

import com.example.sb1024.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    // 추가적인 쿼리 메서드가 필요하면 여기에 정의
}
