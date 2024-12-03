package com.example.sb1202_a.assignment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private Long assignmentId;
    private Long classId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String feedback;
    private LocalDateTime createdAt;
}