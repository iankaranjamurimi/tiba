package com.tiba.tiba.DTO;

import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HospitalStaffDTO {

    public Integer getUser;
    private Long id;
    private String email;
    private String phoneNumber;
    private String specialization;
    private Long userId;
//    @Past(message = "The Date of birth must be in the past")
//    private LocalDate dateOfBirth;


    public Integer getHospitalStaff_Id() {
        return null;
    }
}

