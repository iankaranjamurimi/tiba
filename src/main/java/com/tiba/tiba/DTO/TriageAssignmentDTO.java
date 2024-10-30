package com.tiba.tiba.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class TriageAssignmentDTO {

    private Long id;
    private Long patientId;
    private Long nurseId;
    private LocalDateTime assignmentTime; // Assuming assignmentTime is of type LocalDateTime
    private String status;
}

