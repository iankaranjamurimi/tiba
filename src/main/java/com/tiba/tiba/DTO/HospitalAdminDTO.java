package com.tiba.tiba.DTO;

import com.tiba.tiba.Entities.UserRole;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;


@Data
public class HospitalAdminDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    private String password;
    private UserRole roles;
    private String phoneNumber;
    private String gender;
    private String idNumber;
    private LocalDate dateOfBirth;
    private String address;
    private String nationality;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalLocation;
    private String hospitalContactNumber;
}

