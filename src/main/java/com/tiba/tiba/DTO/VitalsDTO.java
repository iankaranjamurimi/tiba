package com.tiba.tiba.DTO;

import lombok.Data;

@Data
public class VitalsDTO {
    private Integer temperature;
    private Integer bpSystolic;
    private Integer bpDiastolic;
    private Integer heartRate;
    private Integer respiratoryRate;
    private Integer oxygenSaturation;
    private Integer weight;
    private Long medicalRecordsId;
    private Long userId;
}