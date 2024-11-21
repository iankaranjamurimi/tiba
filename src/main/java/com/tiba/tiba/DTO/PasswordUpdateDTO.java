package com.tiba.tiba.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordUpdateDTO {
    @NotBlank(message = "Current password required")
    private String currentPassword;

    @NotBlank(message = "New password required")
    private String newPassword;

    @NotBlank(message = "Password confirmation required")
    private String confirmPassword;
}
