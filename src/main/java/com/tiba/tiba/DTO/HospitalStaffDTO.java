package com.tiba.tiba.DTO;

import com.tiba.tiba.Entities.UserRole;
import lombok.Data;

@Data
public class HospitalStaffDTO {
    private String firstName;
    private String middleName;
    private String lastName;
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

