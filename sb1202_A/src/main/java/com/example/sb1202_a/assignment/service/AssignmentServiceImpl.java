package com.example.sb1202_a.assignment.service;

import com.example.sb1202_a.assignment.dto.AssignmentDTO;
import com.example.sb1202_a.assignment.entity.AssignmentEntity;
import com.example.sb1202_a.assignment.repository.AssignmentRepository;
import com.example.sb1202_a.class1.entity.ClassEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;

    @Override
    public List<AssignmentDTO> selectAssignmentList() throws Exception {
        List<AssignmentEntity> entityList = assignmentRepository.findAll();
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentDTO selectAssignment(Long assignmentId) throws Exception {
        AssignmentEntity entity = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new Exception("과제를 찾을 수 없습니다."));
        return convertToDTO(entity);
    }

    @Override
    public List<AssignmentDTO> selectAssignmentByClass(Long classId) throws Exception {
        // ClassEntity 객체 생성
        ClassEntity classInfo = ClassEntity.builder()
                .classId(classId)
                .build();

        // 해당 수업의 과제 목록 조회
        List<AssignmentEntity> entityList = assignmentRepository.findByClassInfo(classInfo);

        // Entity를 DTO로 변환하여 반환
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertAssignment(AssignmentDTO assignmentDTO) throws Exception {
        AssignmentEntity entity = convertToEntity(assignmentDTO);
        assignmentRepository.save(entity);
    }

    @Override
    public void updateAssignment(AssignmentDTO assignmentDTO) throws Exception {
        AssignmentEntity entity = assignmentRepository.findById(assignmentDTO.getAssignmentId())
                .orElseThrow(() -> new Exception("과제를 찾을 수 없습니다."));

        entity.setTitle(assignmentDTO.getTitle());
        entity.setDescription(assignmentDTO.getDescription());
        entity.setDueDate(assignmentDTO.getDueDate());
        entity.setFeedback(assignmentDTO.getFeedback());

        assignmentRepository.save(entity);
    }

    @Override
    public void deleteAssignment(Long assignmentId) throws Exception {
        assignmentRepository.deleteById(assignmentId);
    }

    private AssignmentDTO convertToDTO(AssignmentEntity entity) {
        return AssignmentDTO.builder()
                .assignmentId(entity.getAssignmentId())
                .classId(entity.getClassInfo().getClassId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .dueDate(entity.getDueDate())
                .feedback(entity.getFeedback())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private AssignmentEntity convertToEntity(AssignmentDTO dto) {
        return AssignmentEntity.builder()
                .assignmentId(dto.getAssignmentId())
                .classInfo(ClassEntity.builder().classId(dto.getClassId()).build())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .feedback(dto.getFeedback())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
