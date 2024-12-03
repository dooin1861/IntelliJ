package com.example.sb1202_a.class1.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
    private Long classId;
    private Long subjectId;
    private Long professorId;
    private String semester;
    private Integer year;
    private Integer maxStudents;
    private String classTime;
    private String classroom;
}
