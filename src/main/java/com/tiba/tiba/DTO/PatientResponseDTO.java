package com.tiba.tiba.DTO;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class PatientResponseDTO {
    private Long userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private String idNumber;
    private String contactNumber;
}
