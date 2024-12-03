package com.example.sb1202_a.class1.service;

import com.example.sb1202_a.class1.dto.ClassDTO;

import java.util.List;

public interface ClassService {
    List<ClassDTO> selectClassList() throws Exception;
    ClassDTO selectClass(Long classId) throws Exception;
    void insertClass(ClassDTO classDTO) throws Exception;
    void updateClass(ClassDTO classDTO) throws Exception;
    void deleteClass(Long classId) throws Exception;
    List<ClassDTO> selectClassByProfessor(Long professorId) throws Exception;
}