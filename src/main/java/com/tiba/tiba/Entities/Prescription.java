package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue
    private Long prescriptionId;

    private String dosage;
    private String frequency;
    private String duration;
    private Integer quantity;
    private Integer refills = 0;
    private LocalDate prescribedDate;
    private String status = "active";
    private String notes;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "hospitalStaff_id")
    private HospitalStaff hospitalStaff;






}
