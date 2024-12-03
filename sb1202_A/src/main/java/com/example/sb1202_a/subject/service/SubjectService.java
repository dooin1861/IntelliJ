package com.example.sb1202_a.subject.service;

import com.example.sb1202_a.subject.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    List<SubjectDTO> selectSubjectList() throws Exception;
    SubjectDTO selectSubject(Long subjectId) throws Exception;
    void insertSubject(SubjectDTO subjectDTO) throws Exception;
    void updateSubject(SubjectDTO subjectDTO) throws Exception;
    void deleteSubject(Long subjectId) throws Exception;
}
