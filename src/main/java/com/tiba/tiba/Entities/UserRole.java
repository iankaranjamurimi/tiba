package com.tiba.tiba.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Getter
public enum UserRole {

    PATIENT,
    DOCTOR,
    TRIAGE_NURSE,
    RECEPTIONIST,
    LAB_TECHNICIAN,
    IMAGING_TECHNICIAN,
    HOSPITAL_ADMIN,
    SUPER_ADMIN
}