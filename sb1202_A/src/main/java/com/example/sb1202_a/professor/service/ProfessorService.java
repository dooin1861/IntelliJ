package com.example.sb1202_a.professor.service;

import com.example.sb1202_a.professor.dto.ProfessorDTO;

import java.util.List;

public interface ProfessorService {
    List<ProfessorDTO> selectProfessorList() throws Exception;
    ProfessorDTO selectProfessor(Long professorId) throws Exception;
    void insertProfessor(ProfessorDTO professorDTO) throws Exception;
    void updateProfessor(ProfessorDTO professorDTO) throws Exception;
    void deleteProfessor(Long professorId) throws Exception;
}
