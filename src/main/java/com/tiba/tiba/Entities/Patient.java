package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    // One Patient many medical records
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecords> medicalRecords = new ArrayList<>();

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Vitals vitals;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Allergies> allergies = new ArrayList<>();


    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Appointments appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> prescription  = new ArrayList<>();

}
