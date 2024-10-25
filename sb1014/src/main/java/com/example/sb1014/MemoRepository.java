package com.example.sb1014;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByIdBetween(Long min, Long max); // 긴 문장이 쿼리 메서드.
    List<Memo> findByIdBetweenOrderByIdDesc(Long min, Long max);

    @Query("select m from Memo m order by m.id desc")
    List<Memo> getListDesc();

    @Query("select count(m) from Memo m")
    int getCount();

    @Query("select m from Memo m where m.id = :id")
    Memo getById(Long id);
}
