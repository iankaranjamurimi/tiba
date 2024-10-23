package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.util.Set;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Users")
public class User {


    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Patient patient;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private HospitalStaff hospitalStaff;


    public enum UserRole {

        PATIENT,
        DOCTOR,
        TRIAGE_NURSE,
        RECEPTIONIST,
        LAB_TECHNICIAN,
        IMAGING_TECHNICIAN,
        HOSPITAL_ADMIN,
        SUPER_ADMIN
    }


}
