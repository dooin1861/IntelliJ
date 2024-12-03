package com.example.sb1202_a.grade.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private Long gradeId;
    private Long enrollmentId;
    private BigDecimal midtermScore;
    private BigDecimal finalScore;
    private BigDecimal assignmentScore;
    private BigDecimal attendanceScore;
    private BigDecimal totalScore;
    private String finalGrade;
}
