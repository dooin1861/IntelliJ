package com.example.sb1202_a.subject.service;

import com.example.sb1202_a.subject.dto.SubjectDTO;
import com.example.sb1202_a.subject.entity.SubjectEntity;
import com.example.sb1202_a.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectDTO> selectSubjectList() throws Exception {
        List<SubjectEntity> entityList = subjectRepository.findAll();
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO selectSubject(Long subjectId) throws Exception {
        SubjectEntity entity = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new Exception("과목을 찾을 수 없습니다."));
        return convertToDTO(entity);
    }

    @Override
    public void insertSubject(SubjectDTO subjectDTO) throws Exception {
        SubjectEntity entity = convertToEntity(subjectDTO);
        subjectRepository.save(entity);
    }

    @Override
    public void updateSubject(SubjectDTO subjectDTO) throws Exception {
        SubjectEntity entity = subjectRepository.findById(subjectDTO.getSubjectId())
                .orElseThrow(() -> new Exception("과목을 찾을 수 없습니다."));

        entity.setSubjectCode(subjectDTO.getSubjectCode());
        entity.setSubjectName(subjectDTO.getSubjectName());
        entity.setCredits(subjectDTO.getCredits());
        entity.setDepartment(subjectDTO.getDepartment());

        subjectRepository.save(entity);
    }

    @Override
    public void deleteSubject(Long subjectId) throws Exception {
        subjectRepository.deleteById(subjectId);
    }

    private SubjectDTO convertToDTO(SubjectEntity entity) {
        return SubjectDTO.builder()
                .subjectId(entity.getSubjectId()) // 엔티티의 ID를 DTO에 설정
                .subjectCode(entity.getSubjectCode()) // 엔티티의 과목 코드를 DTO에 설정
                .subjectName(entity.getSubjectName()) // 엔티티의 과목 이름을 DTO에 설정
                .credits(entity.getCredits())
                .department(entity.getDepartment())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private SubjectEntity convertToEntity(SubjectDTO dto) {
        return SubjectEntity.builder()
                .subjectId(dto.getSubjectId())
                .subjectCode(dto.getSubjectCode())
                .subjectName(dto.getSubjectName())
                .credits(dto.getCredits())
                .department(dto.getDepartment())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
