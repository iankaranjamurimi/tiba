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
public class Referral {
    @Id
    @GeneratedValue
    private Long id;
    //referringDoctorId
    private Long referringHospitalStaffId;

    private Long referredHospitalStaffId;

    private Long patientId;

    private Long referringHospitalId;

    private Long referredHospitalId;

    private String reason;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private ReferralStatus status;
}




