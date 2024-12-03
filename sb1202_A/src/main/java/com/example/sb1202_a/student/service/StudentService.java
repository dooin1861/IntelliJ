package com.example.sb1202_a.student.service;

import com.example.sb1202_a.student.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> selectStudentList() throws Exception;

    StudentDTO selectStudent(Long studentId) throws Exception;

    void insertStudent(StudentDTO studentDTO) throws Exception;

    void updateStudent(StudentDTO studentDTO) throws Exception;

    void deleteStudent(Long studentId) throws Exception;
}
