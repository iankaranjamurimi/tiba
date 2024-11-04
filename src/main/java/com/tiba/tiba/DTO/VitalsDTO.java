package com.tiba.tiba.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class VitalsDTO {

    private Integer temperature;
    private Integer bpSystolic;
    private Integer bpDiastolic;
    private Integer heartRate;
    private Integer respiratoryRate;
    private Integer oxygenSaturation;
    private Integer weight;
    private Long medicalRecordsId;

}
