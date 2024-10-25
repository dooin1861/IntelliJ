package com.example.sb1018.repository;

import com.example.sb1018.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 원칙은 애노테이션을 써야하지만 생략 가능.
public interface MyDataRepository extends JpaRepository<Emp, Long> {
}
