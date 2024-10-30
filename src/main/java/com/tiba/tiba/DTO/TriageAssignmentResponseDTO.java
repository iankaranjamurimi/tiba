package com.tiba.tiba.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TriageAssignmentResponseDTO {

        private boolean success;
        private String message;
        private TriageAssignmentDTO triageDetails;
    }


