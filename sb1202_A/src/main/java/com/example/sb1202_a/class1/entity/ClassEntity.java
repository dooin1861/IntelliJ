package com.example.sb1202_a.class1.entity;

import com.example.sb1202_a.assignment.entity.AssignmentEntity;
import com.example.sb1202_a.enrollment.entity.EnrollmentEntity;
import com.example.sb1202_a.professor.entitiy.ProfessorEntity;
import com.example.sb1202_a.subject.entity.SubjectEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classes")
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;

    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    private Integer year;

    private Integer maxStudents;
    private String classTime;
    private String classroom;

    @Builder.Default
    @OneToMany(mappedBy = "classInfo")
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "classInfo")
    private List<AssignmentEntity> assignments = new ArrayList<>();
}
