package com.tiba.tiba.DTO;



import com.tiba.tiba.Entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class HospitalStaffDTO {

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
//    @Size(min = 1, message = "At least one role is required")
    private UserRole roles;


    private String phoneNumber;
    private String gender;
    private Integer idNumber;
    private String dateOfBirth;
    private String address;
    private String nationality;
    private String hospitalname;


}

