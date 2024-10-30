package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Vitals {
    @Id
    @GeneratedValue
    private Long id;

    private Integer temperature;

    private Integer bpSystolic;

    private Integer bpDiastolic;

    private Integer heartRate;

    private Integer respiratoryRate;

    private Integer oxygenSaturation;


    @OneToOne
    @JoinColumn(name = "medicalRecords_id")
    private MedicalRecords medicalRecords;
//    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
}
