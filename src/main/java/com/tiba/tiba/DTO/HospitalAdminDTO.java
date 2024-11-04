package com.tiba.tiba.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tiba.tiba.Entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class HospitalAdminDTO {

    @Email(message = "Email should be valid")
    @NotBlank(message = "The Email is mandatory")
    private String email;


    @NotBlank(message = "The Password is mandatory")
    @Size(min = 6, message = "The Password should be at least 6 characters")
    private String password;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 10, message = "First name should be at most 10 characters")
    private String firstName;

    @NotBlank(message = "Middle name is mandatory")
    @Size(max = 10, message = "Middle name should be at most 10 characters")
    @JsonProperty("middleName")
    private String middleName;

    @NotBlank(message = "The Last name is mandatory")
    @Size(max = 10, message = "Last name should be at most 10 characters")
    private String lastName;

    @NotBlank(message = "The National Id Number is mandatory")
    @Size(max = 10, message = "The National Id Number should be at most 10 characters")
    private String idNumber;


    @NotBlank(message = "Date of Birth is mandatory")
    @Size(max = 10, message = "DOB should be at most 10 characters")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is mandatory")
    @Size(max = 10, message = "Gender should be at most 6 characters")
    private String gender;


    @NotBlank(message = "Contact Number is mandatory")
    @Size(max = 10, message = "Contact Number should be at most 10 characters")
    private String contactNumber;

    @NotBlank(message = "the role is mandatory")
    @Size(max = 20, message = "the role should be at most 20 characters")
    private UserRole roles;


    public @Email(message = "Email should be valid") @NotBlank(message = "Email is mandatory") String getEmail() {
        return email;
    }


    public @NotBlank(message = "Password is mandatory") @Size(min = 6, message = "Password should be at least 6 characters") String getPassword() {
        return password;
    }


    public @NotBlank(message = "First name is mandatory") @Size(max = 10, message = "First name should be at most 10 characters") String getFirstname() {
        return firstName;
    }

    public @NotBlank(message = "middle name is mandatory") @Size(max = 10, message = "middle name should be at most 10 characters") String getMiddleName() {
        return middleName;
    }

    public @NotBlank(message = "Last name is mandatory") @Size(max = 10, message = "Last name should be at most 10 characters") String getLastname() {
        return lastName;
    }

    public @NotBlank(message = "The National Id Number is mandatory") @Size(max = 10, message = "The National Id Number should be at most 10 characters") String getIdNumber() {
        return idNumber;
    }

    public @NotBlank(message = "Date of Birth is mandatory") @Size(max = 10, message = "Date of Birth should be at most 10 characters") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public @NotBlank(message = "Gender is mandatory") @Size(max = 6, message = "Gender should be at most 6 characters") String getGender() {
        return gender;
    }


    public @NotBlank(message = "Contact Number is mandatory") @Size(max = 10, message = "Last name should be at most 10 characters") String getContactNumber() {
        return contactNumber;

    }

    public@NotBlank(message = "Hospital Name is mandatory") @Size(max = 20, message = "Hospital name should be at most 20 characters")String getHospitalName() {
        return "hospitalName";
    }

    public String getHospitalAddress() {
        return "hospitalAddress";
    }

    public String getHospitalLocation() {
        return "hospitalLocation";
    }

    public String getHospitalContactNumber() {
        return "hospitalContactNumber";
    }

    public String getFirstName() {
        return "firstName";
    }

    public String getLastName() {
        return "lastName";
    }

    public String getAddress() {
        return "address";
    }

    public String Email() {
            return "email";
    }
}

