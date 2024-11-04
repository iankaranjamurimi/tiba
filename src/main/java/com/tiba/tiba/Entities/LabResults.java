package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class LabResults {
    @Id
    @GeneratedValue
    private Long id;
    private String testName;
    private String testCode;
    private String resultValue;
    private String unit;
    private String referenceRange;
    private String status; // e.g., pending, completed, cancelled
    private LocalDateTime performedAt;
    private LocalDateTime reportedAt;
    private String notes;

    @OneToOne
    @JoinColumn(name = "medicalRecords_id")
    private MedicalRecords medicalRecords;


}
