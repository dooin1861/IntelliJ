package com.example.sb1202_a.enrollment.repository;

import com.example.sb1202_a.class1.entity.ClassEntity;
import com.example.sb1202_a.enrollment.entity.EnrollmentEntity;
import com.example.sb1202_a.enrollment.entity.EnrollmentStatus;
import com.example.sb1202_a.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
    List<EnrollmentEntity> findByStudent(StudentEntity student);
    List<EnrollmentEntity> findByClassInfo(ClassEntity classInfo);
    List<EnrollmentEntity> findByStatus(EnrollmentStatus status);
}
