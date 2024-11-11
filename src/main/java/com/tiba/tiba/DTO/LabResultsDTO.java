package com.tiba.tiba.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LabResultsDTO {

//    private Long resultId;
    private String testName;
    private String testCode;
    private String resultValue;
    private String unit;
    private String referenceRange;
    private String status;
    private LocalDateTime performedAt;
    private LocalDateTime reportedAt;
    private String notes;
    private Long medicalRecordsId;


//    public Long getMedicalRecordsId() {
//        return null;
//    }
//
//    public void setMedicalRecordsId(Long id) {
//
//    }
}
