package com.example.sb1202_a.enrollment.service;

import com.example.sb1202_a.class1.entity.ClassEntity;
import com.example.sb1202_a.enrollment.dto.EnrollmentDTO;
import com.example.sb1202_a.enrollment.entity.EnrollmentEntity;
import com.example.sb1202_a.enrollment.repository.EnrollmentRepository;
import com.example.sb1202_a.student.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public List<EnrollmentDTO> selectEnrollmentList() throws Exception {
        List<EnrollmentEntity> entityList = enrollmentRepository.findAll();
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDTO selectEnrollment(Long enrollmentId) throws Exception {
        EnrollmentEntity entity = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new Exception("수강신청을 찾을 수 없습니다."));
        return convertToDTO(entity);
    }

    @Override
    public List<EnrollmentDTO> selectEnrollmentByStudent(Long studentId) throws Exception {
        // StudentEntity 객체 생성
        StudentEntity student = StudentEntity.builder()
                .studentId(studentId)
                .build();

        // 학생의 수강신청 목록 조회
        List<EnrollmentEntity> entityList = enrollmentRepository.findByStudent(student);

        // Entity를 DTO로 변환하여 반환
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertEnrollment(EnrollmentDTO enrollmentDTO) throws Exception {
        EnrollmentEntity entity = convertToEntity(enrollmentDTO);
        enrollmentRepository.save(entity);
    }

    @Override
    public void updateEnrollment(EnrollmentDTO enrollmentDTO) throws Exception {
        EnrollmentEntity entity = enrollmentRepository.findById(enrollmentDTO.getEnrollmentId())
                .orElseThrow(() -> new Exception("수강신청을 찾을 수 없습니다."));

        entity.setStatus(enrollmentDTO.getStatus());
        enrollmentRepository.save(entity);
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) throws Exception {
        enrollmentRepository.deleteById(enrollmentId);
    }

    private EnrollmentDTO convertToDTO(EnrollmentEntity entity) {
        return EnrollmentDTO.builder()
                .enrollmentId(entity.getEnrollmentId())
                .classId(entity.getClassInfo().getClassId())
                .studentId(entity.getStudent().getStudentId())
                .enrollmentDate(entity.getEnrollmentDate())
                .status(entity.getStatus())
                .build();
    }

    private EnrollmentEntity convertToEntity(EnrollmentDTO dto) {
        return EnrollmentEntity.builder()
                .enrollmentId(dto.getEnrollmentId())
                .classInfo(ClassEntity.builder().classId(dto.getClassId()).build())
                .student(StudentEntity.builder().studentId(dto.getStudentId()).build())
                .enrollmentDate(dto.getEnrollmentDate())
                .status(dto.getStatus())
                .build();
    }
}
