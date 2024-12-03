package com.example.sb1202_a.assignment.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentSubmissionDTO {
    private Long submissionId;
    private Long assignmentId;
    private Long studentId;
    private String submissionContent;
    private LocalDateTime submissionDate;
    private String feedback;
    private LocalDateTime feedbackDate;
    private BigDecimal score;
}
