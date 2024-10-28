package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue
    private Integer id;

    private String hospitalName;

    private String hospitalAddress;

    private String hospitalLocation;

    private String hospitalContactNumber;

    @OneToOne
    @JoinColumn(name = "hospital_admin_id")
    private HospitalAdmin hospitalAdmin;


    public static void setDateOfBirth(@NotBlank(message = "Date of Birth is mandatory") @Size(max = 10, message = "Date of Birth should be at most 10 characters") LocalDate dateOfBirth) {

    }

    public static void setGender(@NotBlank(message = "Gender is mandatory") @Size(max = 6, message = "Gender should be at most 6 characters") String gender) {

    }

    public static void setIdNumber(@NotBlank(message = "The National Id Number is mandatory") @Size(max = 10, message = "The National Id Number should be at most 10 characters") String idNumber) {

    }

    public static void setContactNumber(@NotBlank(message = "Contact Number is mandatory") @Size(max = 10, message = "Last name should be at most 10 characters") String contactNumber) {

    }

    public static void setRoles(@NotBlank(message = "the role is mandatory") @Size(max = 20, message = "the role should be at most 20 characters") UserRole roles) {

    }
}
