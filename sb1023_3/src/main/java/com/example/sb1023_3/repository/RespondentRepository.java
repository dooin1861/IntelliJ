package com.example.sb1023_3.repository;

import com.example.sb1023_3.entity.Respondent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespondentRepository extends JpaRepository<Respondent, Long> {
}