package com.example.sb1202_a.completion.service;

import com.example.sb1202_a.completion.dto.CompletionDTO;

import java.util.List;

public interface CompletionService {
    List<CompletionDTO> selectCompletionList() throws Exception;
    CompletionDTO selectCompletion(Long completionId) throws Exception;
    void insertCompletion(CompletionDTO completionDTO) throws Exception;
    void updateCompletion(CompletionDTO completionDTO) throws Exception;
    void deleteCompletion(Long completionId) throws Exception;
    List<CompletionDTO> selectCompletionByStudent(Long studentId) throws Exception;
}
