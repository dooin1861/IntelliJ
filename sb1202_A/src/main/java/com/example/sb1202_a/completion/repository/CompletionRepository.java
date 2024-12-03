package com.example.sb1202_a.completion.repository;

import com.example.sb1202_a.completion.entity.CompletionEntity;
import com.example.sb1202_a.completion.entity.CompletionStatus;
import com.example.sb1202_a.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompletionRepository extends JpaRepository<CompletionEntity, Long> {
    // 학생별 수료 정보 조회
    List<CompletionEntity> findByStudent(StudentEntity student);

    // 학생과 년도, 학기로 수료 정보 조회
    Optional<CompletionEntity> findByStudentAndYearAndSemester(
            StudentEntity student,
            Integer year,
            String semester
    );

    // 특정 년도, 학기의 모든 수료 정보 조회
    List<CompletionEntity> findByYearAndSemester(Integer year, String semester);

    // 상태별 수료 정보 조회
    List<CompletionEntity> findByStatus(CompletionStatus status);

    // 특정 년도의 모든 수료 정보 조회
    List<CompletionEntity> findByYear(Integer year);
}
