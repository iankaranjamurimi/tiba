package com.tiba.tiba.DTO;

import com.tiba.tiba.Entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


//@Data
@Getter
@Setter
@RequiredArgsConstructor

public class HospitalAdminDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "the role is mandatory")
//    @Size(max = 20, message = "the role should be at most 20 characters")
    private UserRole roles;


    private String phoneNumber;
    private String gender;
    private String idNumber;
    private LocalDate dateOfBirth;
    private String address;
    private String nationality;

    public String getHospitalName() {
            return null;
    }

    public String getHospitalAddress() {
            return null;
    }

    public String getHospitalLocation() {
        return null;
    }

    public String getHospitalContactNumber() {
        return null;
    }

    public String Email() {
        return null;
    }

}

