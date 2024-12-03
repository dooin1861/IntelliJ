package com.example.sb1202_a.subject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private Long subjectId;
    private String subjectCode;
    private String subjectName;
    private Integer credits;
    private String department;
    private LocalDateTime createdAt;
}
