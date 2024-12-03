package com.example.sb1202_a.grade.repository;

import com.example.sb1202_a.enrollment.entity.EnrollmentEntity;
import com.example.sb1202_a.grade.entity.GradeEntity;
import com.example.sb1202_a.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
    Optional<GradeEntity> findByEnrollment(EnrollmentEntity enrollment);
    List<GradeEntity>findByEnrollmentStudent(StudentEntity student);
}
