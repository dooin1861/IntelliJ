package com.example.sb1202_a.assignment.entity;

import com.example.sb1202_a.class1.entity.ClassEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assignments")
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassEntity classInfo;  // class -> classInfo로 변경

    @Column(nullable = false)
    private String title;

    private String description;
    private LocalDateTime dueDate;
    private String feedback;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder.Default
    @OneToMany(mappedBy = "assignment")
    private List<AssignmentSubmissionEntity> submissions = new ArrayList<>();
}