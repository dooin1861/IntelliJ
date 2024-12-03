package com.example.sb1202_a.class1.service;

import com.example.sb1202_a.class1.dto.ClassDTO;
import com.example.sb1202_a.class1.entity.ClassEntity;
import com.example.sb1202_a.class1.repository.ClassRepository;
import com.example.sb1202_a.professor.entitiy.ProfessorEntity;
import com.example.sb1202_a.subject.entity.SubjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;

    @Override
    public List<ClassDTO> selectClassList() throws Exception {
        List<ClassEntity> entityList = classRepository.findAll();
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClassDTO selectClass(Long classId) throws Exception {
        ClassEntity entity = classRepository.findById(classId)
                .orElseThrow(() -> new Exception("수업을 찾을 수 없습니다."));
        return convertToDTO(entity);
    }

    @Override
    public List<ClassDTO> selectClassByProfessor(Long professorId) throws Exception {
        // ProfessorEntity 객체 생성
        ProfessorEntity professor = ProfessorEntity.builder()
                .professorId(professorId)
                .build();

        // 교수의 수업 목록 조회
        List<ClassEntity> entityList = classRepository.findByProfessor(professor);

        // Entity를 DTO로 변환하여 반환
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertClass(ClassDTO classDTO) throws Exception {
        ClassEntity entity = convertToEntity(classDTO);
        classRepository.save(entity);
    }

    @Override
    public void updateClass(ClassDTO classDTO) throws Exception {
        ClassEntity entity = classRepository.findById(classDTO.getClassId())
                .orElseThrow(() -> new Exception("수업을 찾을 수 없습니다."));

        entity.setSemester(classDTO.getSemester());
        entity.setYear(classDTO.getYear());
        entity.setMaxStudents(classDTO.getMaxStudents());
        entity.setClassTime(classDTO.getClassTime());
        entity.setClassroom(classDTO.getClassroom());

        classRepository.save(entity);
    }

    @Override
    public void deleteClass(Long classId) throws Exception {
        classRepository.deleteById(classId);
    }

    private ClassDTO convertToDTO(ClassEntity entity) {
        return ClassDTO.builder()
                .classId(entity.getClassId())
                .subjectId(entity.getSubject().getSubjectId())
                .professorId(entity.getProfessor().getProfessorId())
                .semester(entity.getSemester())
                .year(entity.getYear())
                .maxStudents(entity.getMaxStudents())
                .classTime(entity.getClassTime())
                .classroom(entity.getClassroom())
                .build();
    }

    private ClassEntity convertToEntity(ClassDTO dto) {
        return ClassEntity.builder()
                .classId(dto.getClassId())
                .subject(SubjectEntity.builder().subjectId(dto.getSubjectId()).build())
                .professor(ProfessorEntity.builder().professorId(dto.getProfessorId()).build())
                .semester(dto.getSemester())
                .year(dto.getYear())
                .maxStudents(dto.getMaxStudents())
                .classTime(dto.getClassTime())
                .classroom(dto.getClassroom())
                .build();
    }
}
