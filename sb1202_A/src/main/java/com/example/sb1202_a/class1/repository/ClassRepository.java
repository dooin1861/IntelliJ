package com.example.sb1202_a.class1.repository;

import com.example.sb1202_a.class1.entity.ClassEntity;
import com.example.sb1202_a.professor.entitiy.ProfessorEntity;
import com.example.sb1202_a.subject.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    List<ClassEntity> findByProfessor(ProfessorEntity professor);
    List<ClassEntity> findBySubject(SubjectEntity subject);
    List<ClassEntity> findBySemesterAndYear(String semester, Integer year);
}