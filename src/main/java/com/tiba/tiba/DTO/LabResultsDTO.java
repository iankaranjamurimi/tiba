package com.tiba.tiba.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Data
public class LabResultsDTO {
    private String testName;
    private String testResult;
    private String notes;
    private LocalDateTime performedAt;
    private String hospitalName;
    private Long medicalRecordsId;

}
