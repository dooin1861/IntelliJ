package com.example.sb1202_a.assignment.repository;

import com.example.sb1202_a.assignment.entity.AssignmentEntity;
import com.example.sb1202_a.assignment.entity.AssignmentSubmissionEntity;
import com.example.sb1202_a.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmissionEntity, Long> {
    List<AssignmentSubmissionEntity> findByAssignment(AssignmentEntity assignment);
    List<AssignmentSubmissionEntity> findByStudent(StudentEntity student);
    Optional<AssignmentSubmissionEntity> findByAssignmentAndStudent(AssignmentEntity assignment, StudentEntity student);
}
