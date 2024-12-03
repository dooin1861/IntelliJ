package com.example.sb1202_a.grade.entity;

import com.example.sb1202_a.enrollment.entity.EnrollmentEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grades")
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @OneToOne
    @JoinColumn(name = "enrollment_id")
    private EnrollmentEntity enrollment;

    private BigDecimal midtermScore;
    private BigDecimal finalScore;
    private BigDecimal assignmentScore;
    private BigDecimal attendanceScore;
    private BigDecimal totalScore;
    private String finalGrade;
}
