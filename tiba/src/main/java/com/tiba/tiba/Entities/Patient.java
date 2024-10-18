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

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String emergencyContactNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String gender;

    private Integer age;

    @Past  (message = "ie. the DOB must be in the past")
    @Column
    private LocalDate dateOfBirth;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
