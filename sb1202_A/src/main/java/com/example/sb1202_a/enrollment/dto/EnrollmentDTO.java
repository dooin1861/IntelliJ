package com.example.sb1202_a.enrollment.dto;

import com.example.sb1202_a.enrollment.entity.EnrollmentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {
    private Long enrollmentId;
    private Long classId;
    private Long studentId;
    private LocalDateTime enrollmentDate;
    private EnrollmentStatus status;
}
