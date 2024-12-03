package com.example.sb1202_a.completion.service;

import com.example.sb1202_a.completion.dto.CompletionDTO;
import com.example.sb1202_a.completion.entity.CompletionEntity;
import com.example.sb1202_a.completion.repository.CompletionRepository;
import com.example.sb1202_a.student.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompletionServiceImpl implements CompletionService {
    private final CompletionRepository completionRepository;

    @Override
    public List<CompletionDTO> selectCompletionList() throws Exception {
        List<CompletionEntity> entityList = completionRepository.findAll();
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompletionDTO selectCompletion(Long completionId) throws Exception {
        CompletionEntity entity = completionRepository.findById(completionId)
                .orElseThrow(() -> new Exception("이수 정보를 찾을 수 없습니다."));
        return convertToDTO(entity);
    }

    @Override
    public void insertCompletion(CompletionDTO completionDTO) throws Exception {
        CompletionEntity entity = convertToEntity(completionDTO);
        completionRepository.save(entity);
    }

    @Override
    public void updateCompletion(CompletionDTO completionDTO) throws Exception {
        CompletionEntity entity = completionRepository.findById(completionDTO.getCompletionId())
                .orElseThrow(() -> new Exception("이수 정보를 찾을 수 없습니다."));

        entity.setYear(completionDTO.getYear());
        entity.setSemester(completionDTO.getSemester());
        entity.setStatus(completionDTO.getStatus());
        entity.setCompletionDate(completionDTO.getCompletionDate());

        completionRepository.save(entity);
    }

    @Override
    public void deleteCompletion(Long completionId) throws Exception {
        completionRepository.deleteById(completionId);
    }

    @Override
    public List<CompletionDTO> selectCompletionByStudent(Long studentId) throws Exception {
        StudentEntity student = StudentEntity.builder()
                .studentId(studentId)
                .build();
        List<CompletionEntity> entityList = completionRepository.findByStudent(student);
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CompletionDTO convertToDTO(CompletionEntity entity) {
        return CompletionDTO.builder()
                .completionId(entity.getCompletionId())
                .studentId(entity.getStudent().getStudentId())
                .year(entity.getYear())
                .semester(entity.getSemester())
                .completionDate(entity.getCompletionDate())
                .status(entity.getStatus())
                .build();
    }

    private CompletionEntity convertToEntity(CompletionDTO dto) {
        return CompletionEntity.builder()
                .completionId(dto.getCompletionId())
                .student(StudentEntity.builder().studentId(dto.getStudentId()).build())
                .year(dto.getYear())
                .semester(dto.getSemester())
                .completionDate(dto.getCompletionDate())
                .status(dto.getStatus())
                .build();
    }
}
