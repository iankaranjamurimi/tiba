package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @GeneratedValue
    private Integer id;


    private String contactNumber;


    @Column(nullable = true)
    private String emergencyContactNumber;

    @Column(nullable = true)
    private String address;

    @Column(unique = true, nullable = true)
    private String idNumber;

    @Column(nullable = true)
    private String gender;

    @Column(nullable = true)
    private Integer age;


    @Past  (message = "ie. the DOB must be in the past")
    @Column(nullable = true)
    private LocalDate dateOfBirth;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
