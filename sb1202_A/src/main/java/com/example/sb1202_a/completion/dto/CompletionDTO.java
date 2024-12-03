package com.example.sb1202_a.completion.dto;

import com.example.sb1202_a.completion.entity.CompletionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionDTO {
    private Long completionId;
    private Long studentId;
    private Integer year;
    private String semester;
    private LocalDateTime completionDate;
    private CompletionStatus status;
}
