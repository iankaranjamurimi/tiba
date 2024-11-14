package com.tiba.tiba.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientProfileDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private String idNumber;
    private String contactNumber;
    private String address;
    private String emergencyContactNumber;
}
