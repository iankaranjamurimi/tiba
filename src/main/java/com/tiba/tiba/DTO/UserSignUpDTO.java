package com.tiba.tiba.DTO;

import com.tiba.tiba.Entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDTO {

    private String email;

    private String password;

    private String firstname;

    private String middlename;

    private String lastname;

    private String idNumber;

    private LocalDate dateOfBirth;

    private String gender;

    private String contactNumber;

    private UserRole roles;

}