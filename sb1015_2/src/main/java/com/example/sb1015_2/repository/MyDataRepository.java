package com.example.sb1015_2.repository;

import com.example.sb1015_2.entity.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 원칙은 애노테이션을 써야하지만 생략 가능.
public interface MyDataRepository extends JpaRepository<MyData, Long> {
}
