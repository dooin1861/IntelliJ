package com.example.sb1202_a.enrollment.service;

import com.example.sb1202_a.enrollment.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {
    List<EnrollmentDTO> selectEnrollmentList() throws Exception;
    EnrollmentDTO selectEnrollment(Long enrollmentId) throws Exception;
    void insertEnrollment(EnrollmentDTO enrollmentDTO) throws Exception;
    void updateEnrollment(EnrollmentDTO enrollmentDTO) throws Exception;
    void deleteEnrollment(Long enrollmentId) throws Exception;
    List<EnrollmentDTO> selectEnrollmentByStudent(Long studentId) throws Exception;
}
