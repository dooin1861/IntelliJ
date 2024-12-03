package com.example.sb1202_a.professor.repository;

import com.example.sb1202_a.professor.entitiy.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    Optional<ProfessorEntity> findByEmail(String email);
    List<ProfessorEntity> findByDepartment(String department);
}
