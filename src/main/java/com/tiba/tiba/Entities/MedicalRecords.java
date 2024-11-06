package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class MedicalRecords {
    @Id
    @GeneratedValue
    private Long id;

//    @Column(columnDefinition = "TEXT")
    private String notes;

    private String diagnosis;

//    @Column(columnDefinition = "TEXT")
    private String treatment;

//    private String medication;

    private LocalDate submittedAt;

    private String submittedBy;

    // Many medical records belong to one patient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Many medical records belong to one hospitalstaff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalStaff_id")
    private HospitalStaff hospitalStaff;

    // One medical record can have many lab results
    @OneToMany(mappedBy = "medicalRecords", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LabResults> labResults = new ArrayList<>();


}
