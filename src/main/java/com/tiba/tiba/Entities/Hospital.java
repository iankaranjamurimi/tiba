package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue
    private Long id;

    private String hospitalName;

    private String hospitalAddress;

    private String hospitalLocation;

    private String hospitalContactNumber;

    @OneToOne
    @JoinColumn(name = "hospitalAdmin_id")
    private HospitalAdmin hospitalAdmin;


    public static void setIdNumber(@NotBlank(message = "The National Id Number is mandatory") @Size(max = 10, message = "The National Id Number should be at most 10 characters") String idNumber) {

    }

    public void setHospitalAdmin(HospitalAdmin hospitalAdmin) {

    }
}
