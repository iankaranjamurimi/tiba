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
@AllArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue
    private Long id;

    private String dosage;
    private String frequency;
    private String duration;
    private Integer quantity;
//    private Integer refills = 0;
    private LocalDate prescribedDate;
    private String status = "active";
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_staff_id")
    private HospitalStaff hospitalStaff;


    public Prescription() {

    }

    public void setUserId(Long userId) {
        this.user = user;
    }

    public void setHospitalStaffId(Long hospitalStaffId) {
        this.hospitalStaff = new HospitalStaff();
    }
}
