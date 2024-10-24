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
@Table(name = "HospitalAdmin")
public class HospitalAdmin {

    @Id
    @GeneratedValue
    private Integer id;

    private String ContactNumber;

    private String idNumber;

    private String gender;

    private String address;

    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
