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
        private Integer age;
        private String contactNumber;
        private String emergencyContactNumber;
        private String idNumber;
        private String address;
        private String gender;

        @Past(message = "The Date of birth must be in the past")
        private LocalDate dateOfBirth;

        private Integer userId;
    }

