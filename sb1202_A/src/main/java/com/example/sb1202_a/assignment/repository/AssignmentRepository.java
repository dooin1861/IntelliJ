package com.example.sb1202_a.assignment.repository;

import com.example.sb1202_a.assignment.entity.AssignmentEntity;
import com.example.sb1202_a.class1.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
    List<AssignmentEntity> findByClassInfo(ClassEntity classInfo);
    List<AssignmentEntity> findByDueDateBefore(LocalDateTime date);
}
