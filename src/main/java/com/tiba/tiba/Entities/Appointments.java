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
public class Appointments {

    @Id
    @GeneratedValue
    private Long appointmentId;
    private Long hospitalStaffId;
    private String appointmentType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status = "scheduled"; // Default value
    private String notes;
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}