package com.example.sb1202_a.grade.service;

import com.example.sb1202_a.grade.dto.GradeDTO;

import java.util.List;

public interface GradeService {
    List<GradeDTO> selectGradeList() throws Exception;
    GradeDTO selectGrade(Long gradeId) throws Exception;
    void insertGrade(GradeDTO gradeDTO) throws Exception;
    void updateGrade(GradeDTO gradeDTO) throws Exception;
    void deleteGrade(Long gradeId) throws Exception;
    List<GradeDTO> selectGradeByStudent(Long studentId) throws Exception;
}
