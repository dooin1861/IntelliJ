package com.example.sb1202_a.assignment.service;

import com.example.sb1202_a.assignment.dto.AssignmentDTO;

import java.util.List;

public interface AssignmentService {
    List<AssignmentDTO> selectAssignmentList() throws Exception;
    AssignmentDTO selectAssignment(Long assignmentId) throws Exception;
    void insertAssignment(AssignmentDTO assignmentDTO) throws Exception;
    void updateAssignment(AssignmentDTO assignmentDTO) throws Exception;
    void deleteAssignment(Long assignmentId) throws Exception;
    List<AssignmentDTO> selectAssignmentByClass(Long classId) throws Exception;
}
