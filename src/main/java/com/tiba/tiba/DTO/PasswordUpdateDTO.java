package com.tiba.tiba.DTO;

import lombok.Data;

@Data
public class PasswordUpdateDTO {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
