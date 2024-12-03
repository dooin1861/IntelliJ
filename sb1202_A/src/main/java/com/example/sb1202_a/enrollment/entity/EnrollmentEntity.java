package com.example.sb1202_a.enrollment.entity;

import com.example.sb1202_a.class1.entity.ClassEntity;
import com.example.sb1202_a.grade.entity.GradeEntity;
import com.example.sb1202_a.student.entity.StudentEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollments")
public class EnrollmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassEntity classInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Column(name = "enrollment_date")
    private LocalDateTime enrollmentDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @Builder.Default
    @OneToOne(mappedBy = "enrollment")
    private GradeEntity grade = null;
}
