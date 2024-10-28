package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



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

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    private UserRole roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Patient patient;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private HospitalAdmin hospital;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private HospitalStaff hospitalStaff;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private HospitalAdmin hospitalAdmin;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SuperAdmin superAdmin;


}
