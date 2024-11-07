package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "hospital_admin")
public class HospitalAdmin {
    @Id
    @GeneratedValue
    private Integer id;

//    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    private String idNumber;

    private String gender;

    private String address;

    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public static void setEmail(@Email(message = "Email should be valid") @NotBlank(message = "Email is mandatory") String email) {

    }

    public static void setPassword(@NotBlank(message = "Password is mandatory") @Size(min = 6, message = "Password should be at least 6 characters") String password) {

    }

}
