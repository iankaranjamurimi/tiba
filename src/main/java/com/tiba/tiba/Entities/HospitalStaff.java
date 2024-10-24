package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class HospitalStaff {
    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    private String phoneNumber;

    private String specialization;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
