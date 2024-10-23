package com.tiba.tiba.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalStaffDTO {

    public Integer getUser;
    private Long id;
    private String email;
    private String phoneNumber;
    private String specialization;
    private Long userId;


    public Integer getHospitalStaff_Id() {
        return null;
    }
}

