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


    @OneToOne
    @JoinColumn(name = "medicalRecords_Id")
    private MedicalRecords medicalRecords;

    @OneToOne
    @JoinColumn(name = "patient_Id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "hospital_Id")
    private Hospital hospital;


}
