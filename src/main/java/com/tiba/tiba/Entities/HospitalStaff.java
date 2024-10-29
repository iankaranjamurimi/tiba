package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    private LocalDateTime dateOfBirth;

    private String address;

    private String nationality;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
