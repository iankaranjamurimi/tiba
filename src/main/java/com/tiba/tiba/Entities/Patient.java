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
    private Long id;

    @Column(nullable = false)
    private String contactNumber;

    private String emergencyContactNumber;

    private String address;

    @Column(nullable = false)
    private String idNumber;

    private String gender;

    private Integer age;

    @Past  (message = "ie. the DOB must be in the past")

    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
