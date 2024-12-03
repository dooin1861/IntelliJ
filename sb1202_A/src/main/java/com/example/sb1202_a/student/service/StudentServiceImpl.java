package com.example.sb1202_a.student.service;

import com.example.sb1202_a.student.dto.StudentDTO;
import com.example.sb1202_a.student.entity.StudentEntity;
import com.example.sb1202_a.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentDTO> selectStudentList() throws Exception {
        List<StudentEntity> entityList = studentRepository.findAll();
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO selectStudent(Long studentId) throws Exception {
        StudentEntity entity = studentRepository.findById(studentId)
                .orElseThrow(() -> new Exception("학생을 찾을 수 없습니다."));
        return convertToDTO(entity);
    }

    @Override
    public void insertStudent(StudentDTO studentDTO) throws Exception {
        StudentEntity entity = convertToEntity(studentDTO);
        studentRepository.save(entity);
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) throws Exception {
        StudentEntity entity = studentRepository.findById(studentDTO.getStudentId())
                .orElseThrow(() -> new Exception("학생을 찾을 수 없습니다."));

        entity.setName(studentDTO.getName());
        entity.setEmail(studentDTO.getEmail());
        entity.setPhone(studentDTO.getPhone());
        entity.setDepartment(studentDTO.getDepartment());
        entity.setStatus(studentDTO.getStatus());

        studentRepository.save(entity);
    }

    @Override
    public void deleteStudent(Long studentId) throws Exception {
        studentRepository.deleteById(studentId);
    }

    private StudentDTO convertToDTO(StudentEntity entity) {
        return StudentDTO.builder()
                .studentId(entity.getStudentId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .admissionYear(entity.getAdmissionYear())
                .department(entity.getDepartment())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private StudentEntity convertToEntity(StudentDTO dto) {
        return StudentEntity.builder()
                .studentId(dto.getStudentId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .admissionYear(dto.getAdmissionYear())
                .department(dto.getDepartment())
                .status(dto.getStatus())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
