package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class HospitalStaff {
    @Id
    @GeneratedValue
    private Long id;
    private String phoneNumber;
    private String gender;
    private Integer idNumber;
    private LocalDate dateOfBirth;
    private String address;
    private String nationality;
    private String hospitalName;

    @OneToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "hospitalStaff", cascade = CascadeType.ALL)
    private List<Prescription> prescription;

    @OneToMany(mappedBy = "hospitalStaff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointments> appointments  = new ArrayList<>();
}
