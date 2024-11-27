package com.example.sb1127.tab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sb1127.tab.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // 기본적인 CRUD 메서드는 JpaRepository에서 제공됨
    // 필요한 경우 커스텀 쿼리 메서드 추가 가능
}