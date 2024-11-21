package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    private String contactNumber;
    private String emergencyContactNumber;
    private String address;
    private String idNumber;
    private String gender;
    private Integer age;
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
