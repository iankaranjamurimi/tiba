package com.tiba.tiba.Entities;

import com.tiba.tiba.DTO.HospitalAdminDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    private String email;

    private String ContactNumber;

    private String idNumber;

    private String gender;

    private String address;

    private LocalDate dateOfBirth;

    @OneToOne(mappedBy = "hospitalAdmin")
    private Hospital hospital;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public static void setEmail(@Email(message = "Email should be valid") @NotBlank(message = "Email is mandatory") String email) {

    }

    public static void setPassword(@NotBlank(message = "Password is mandatory") @Size(min = 6, message = "Password should be at least 6 characters") String password) {

    }

    public static void setFirstName(@NotBlank(message = "First name is mandatory") @Size(max = 10, message = "First name should be at most 10 characters") String firstname) {

    }

    public static void setMiddleName(@NotBlank(message = "middle name is mandatory") @Size(max = 10, message = "middle name should be at most 10 characters") String middleName) {

    }

    public static void setLastName(@NotBlank(message = "Last name is mandatory") @Size(max = 10, message = "Last name should be at most 10 characters") String lastname) {

    }

    public void register(@Valid HospitalAdminDTO request) {

    }
}
