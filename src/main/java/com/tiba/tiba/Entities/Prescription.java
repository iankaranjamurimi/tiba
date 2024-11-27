package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private Long id;
    private String dosage;
    private String frequency;
    private String duration;
    private Integer quantity;
    private LocalDate prescribedDate;
    private String notes;

    @Enumerated(EnumType.STRING)
    private PrescriptionStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_staff_id")
    private HospitalStaff hospitalStaff;

}
// status: active, completed
