package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecords {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String notes;

    private String diagnosis;

    @Column(columnDefinition = "TEXT")
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


    public void setFollowUpRequired(Boolean followUpRequired) {

    }

    public Boolean getFollowUpRequired() {
            return null;
    }

    public LocalDate getFollowUpDate() {
            return null;
    }

    public void setFollowUpDate(LocalDate followUpDate) {

    }
}
