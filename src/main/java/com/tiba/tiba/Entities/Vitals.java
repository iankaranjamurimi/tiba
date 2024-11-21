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
    private Integer weight;
    private Integer height;
    private String bloodGroup;

    @ManyToOne
    @JoinColumn(name = "medical_Records_id")
    private MedicalRecords medicalRecords;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
