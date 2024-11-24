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
    private String notes;
    private String diagnosis;
    private String treatment;
    private LocalDate submittedAt;
    private String submittedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "medicalRecords", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LabResults> labResults = new ArrayList<>();

}
