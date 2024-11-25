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
    private String testResult;
    private String notes;
    private LocalDateTime performedAt;
    private String hospitalName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalRecords_id")
    private MedicalRecords medicalRecords;


}
