package com.tiba.tiba.DTO;



import com.tiba.tiba.Entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@RequiredArgsConstructor
public class HospitalStaffDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "the role is mandatory")
    @Size(max = 20, message = "the role should be at most 20 characters")
    private UserRole roles;


    private String phoneNumber;
    private String gender;
    private Integer idNumber;
    private String dateOfBirth;
    private String address;
    private String nationality;


}

