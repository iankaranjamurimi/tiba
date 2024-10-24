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


    @Enumerated(EnumType.STRING)
    private UserRole roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Patient patient;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private HospitalStaff hospitalStaff;



}
