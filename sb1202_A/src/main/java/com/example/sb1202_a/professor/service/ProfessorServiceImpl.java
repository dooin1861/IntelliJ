package com.example.sb1202_a.professor.service;

import com.example.sb1202_a.professor.dto.ProfessorDTO;
import com.example.sb1202_a.professor.entitiy.ProfessorEntity;
import com.example.sb1202_a.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;

    @Override
    public List<ProfessorDTO> selectProfessorList() throws Exception {
        List<ProfessorEntity> entityList = professorRepository.findAll();
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProfessorDTO selectProfessor(Long professorId) throws Exception {
        ProfessorEntity entity = professorRepository.findById(professorId)
                .orElseThrow(() -> new Exception("교수를 찾을 수 없습니다."));
        return convertToDTO(entity);
    }

    @Override
    public void insertProfessor(ProfessorDTO professorDTO) throws Exception {
        ProfessorEntity entity = convertToEntity(professorDTO);
        professorRepository.save(entity);
    }

    @Override
    public void updateProfessor(ProfessorDTO professorDTO) throws Exception {
        ProfessorEntity entity = professorRepository.findById(professorDTO.getProfessorId())
                .orElseThrow(() -> new Exception("교수를 찾을 수 없습니다."));

        entity.setName(professorDTO.getName());
        entity.setEmail(professorDTO.getEmail());
        entity.setPhone(professorDTO.getPhone());
        entity.setDepartment(professorDTO.getDepartment());

        professorRepository.save(entity);
    }

    @Override
    public void deleteProfessor(Long professorId) throws Exception {
        professorRepository.deleteById(professorId);
    }

    private ProfessorDTO convertToDTO(ProfessorEntity entity) {
        return ProfessorDTO.builder()
                .professorId(entity.getProfessorId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .department(entity.getDepartment())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private ProfessorEntity convertToEntity(ProfessorDTO dto) {
        return ProfessorEntity.builder()
                .professorId(dto.getProfessorId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .department(dto.getDepartment())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
