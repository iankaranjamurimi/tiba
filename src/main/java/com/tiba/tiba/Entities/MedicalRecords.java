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
public class MedicalRecords {
    @Id
    @GeneratedValue
    private Long id;

    private String notes;

    private String diagnosis;

    private String treatment;

    private String medication;

    private LocalDate submittedAt;

    private String submittedBy;


    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "hospitalStaff_id")
    private HospitalStaff hospitalStaff;

    @OneToOne(mappedBy = "medicalRecords", cascade = CascadeType.ALL)
    private LabResults labResults;

    public void setFollowUpDate(LocalDate followUpDate) {

    }

    public void setFollowUpRequired(Boolean followUpRequired) {

    }

//    follow_up_required BOOLEAN DEFAULT false,
//    follow_up_date DATE




}
