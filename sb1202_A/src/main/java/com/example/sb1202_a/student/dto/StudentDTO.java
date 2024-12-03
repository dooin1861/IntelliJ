package com.example.sb1202_a.student.dto;

import com.example.sb1202_a.student.entity.StudentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long studentId;
    private String name;
    private String email;
    private String phone;
    private Integer admissionYear;
    private String department;
    private StudentStatus status;
    private LocalDateTime createdAt;
}
