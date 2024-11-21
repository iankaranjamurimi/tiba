package com.tiba.tiba.DTO;

import com.tiba.tiba.Entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HospitalStaffDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    private String password;
    private UserRole roles;
    private String phoneNumber;
    private String gender;
    private Integer idNumber;
    private String dateOfBirth;
    private String address;
    private String nationality;
    private String hospitalName;


}

