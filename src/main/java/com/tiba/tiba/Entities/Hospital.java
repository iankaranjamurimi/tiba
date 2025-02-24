package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
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
    @JoinColumn(name = "hospital_Admin_id")
    private HospitalAdmin hospitalAdmin;

    @OneToMany(mappedBy = "hospital")
    private List<MedicalRecords> medicalRecords;

}
