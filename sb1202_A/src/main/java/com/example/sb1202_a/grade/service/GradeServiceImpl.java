package com.example.sb1202_a.grade.service;

import com.example.sb1202_a.enrollment.entity.EnrollmentEntity;
import com.example.sb1202_a.grade.dto.GradeDTO;
import com.example.sb1202_a.grade.entity.GradeEntity;
import com.example.sb1202_a.grade.repository.GradeRepository;
import com.example.sb1202_a.student.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    @Override
    public List<GradeDTO> selectGradeList() throws Exception {
        List<GradeEntity> entityList = gradeRepository.findAll();
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GradeDTO selectGrade(Long gradeId) throws Exception {
        GradeEntity entity = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new Exception("성적을 찾을 수 없습니다."));
        return convertToDTO(entity);
    }

    @Override
    public List<GradeDTO> selectGradeByStudent(Long studentId) throws Exception {
        // StudentEntity 객체 생성
        StudentEntity student = StudentEntity.builder()
                .studentId(studentId)
                .build();

        // 학생의 수강신청 목록으로 성적 조회
        List<GradeEntity> entityList = gradeRepository.findByEnrollmentStudent(student);

        // Entity를 DTO로 변환하여 반환
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertGrade(GradeDTO gradeDTO) throws Exception {
        GradeEntity entity = convertToEntity(gradeDTO);
        gradeRepository.save(entity);
    }

    @Override
    public void updateGrade(GradeDTO gradeDTO) throws Exception {
        GradeEntity entity = gradeRepository.findById(gradeDTO.getGradeId())
                .orElseThrow(() -> new Exception("성적을 찾을 수 없습니다."));

        entity.setMidtermScore(gradeDTO.getMidtermScore());
        entity.setFinalScore(gradeDTO.getFinalScore());
        entity.setAssignmentScore(gradeDTO.getAssignmentScore());
        entity.setAttendanceScore(gradeDTO.getAttendanceScore());
        entity.setTotalScore(gradeDTO.getTotalScore());
        entity.setFinalGrade(gradeDTO.getFinalGrade());

        gradeRepository.save(entity);
    }

    @Override
    public void deleteGrade(Long gradeId) throws Exception {
        gradeRepository.deleteById(gradeId);
    }

    private GradeDTO convertToDTO(GradeEntity entity) {
        return GradeDTO.builder()
                .gradeId(entity.getGradeId())
                .enrollmentId(entity.getEnrollment().getEnrollmentId())
                .midtermScore(entity.getMidtermScore())
                .finalScore(entity.getFinalScore())
                .assignmentScore(entity.getAssignmentScore())
                .attendanceScore(entity.getAttendanceScore())
                .totalScore(entity.getTotalScore())
                .finalGrade(entity.getFinalGrade())
                .build();
    }

    private GradeEntity convertToEntity(GradeDTO dto) {
        return GradeEntity.builder()
                .gradeId(dto.getGradeId())
                .enrollment(EnrollmentEntity.builder().enrollmentId(dto.getEnrollmentId()).build())
                .midtermScore(dto.getMidtermScore())
                .finalScore(dto.getFinalScore())
                .assignmentScore(dto.getAssignmentScore())
                .attendanceScore(dto.getAttendanceScore())
                .totalScore(dto.getTotalScore())
                .finalGrade(dto.getFinalGrade())
                .build();
    }
}
