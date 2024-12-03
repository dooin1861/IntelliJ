package com.example.sb1202_a.subject.repository;

import com.example.sb1202_a.subject.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    Optional<SubjectEntity> findBySubjectCode(String subjectCode);
    List<SubjectEntity> findByDepartment(String department);
}
