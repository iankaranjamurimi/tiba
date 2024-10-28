package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime date;

    //private String status;

    private String reason;

    private String notes;

    @ManyToOne
    private Patient patient;

//    @OneToOne
//    private HospitalStaff hospitalStaff;


}
