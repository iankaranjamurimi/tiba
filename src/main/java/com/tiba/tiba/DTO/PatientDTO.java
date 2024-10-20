package com.tiba.tiba.DTO;

import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class PatientDTO {


        private Integer id;
        private String ContactNumber;
        private String EmergencyContactNumber;
        private String Address;
        private String Gender;

        @Past(message = "The Date of birth must be in the past")
        private LocalDate DateOfBirth;

        private Integer userId;
    }

