package com.tiba.tiba.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLogInDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String password;
}
