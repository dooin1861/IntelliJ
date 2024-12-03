package com.example.sb1202_a.professor.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {
    private Long professorId;
    private String name;
    private String email;
    private String phone;
    private String department;
    private LocalDateTime createdAt;
}
