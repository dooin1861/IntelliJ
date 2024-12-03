package com.example.sb1202_a.student.repository;

import com.example.sb1202_a.student.entity.StudentEntity;
import com.example.sb1202_a.student.entity.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByEmail(String email);
    List<StudentEntity> findByDepartment(String department);
    List<StudentEntity> findByStatus(StudentStatus status);
}
