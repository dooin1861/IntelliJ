package com.example.sb1202_a.completion.entity;

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
@Table(name = "completions")
public class CompletionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long completionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String semester;

    private LocalDateTime completionDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private CompletionStatus status = CompletionStatus.INCOMPLETE;  // 초기값 설정
}
