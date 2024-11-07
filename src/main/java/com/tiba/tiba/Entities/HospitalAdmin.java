package com.tiba.tiba.Entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "hospital_admin")
public class HospitalAdmin {
    @Id
    @GeneratedValue
    private Integer id;

//    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    private String idNumber;

    private String gender;

    private String address;

    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;




}
