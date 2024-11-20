package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Appointments {
    @Id
    @GeneratedValue
//    @Column(name = "appointment_id")
    private Long Id;

    private String appointmentType;

    private LocalDateTime startTime;

    private String status = "scheduled"; // Default value

    private LocalDate submittedAt;

    private String submittedBy;

//    private Long hospitalStaffId;

    @ManyToOne
    @JoinColumn(name = "hospital_Staff_id")
    private HospitalStaff hospitalStaff;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}