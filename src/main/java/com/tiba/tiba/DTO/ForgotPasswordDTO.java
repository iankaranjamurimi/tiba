package com.tiba.tiba.DTO;

import lombok.Data;

@Data
public class ForgotPasswordDTO {
    private String email;
    private String otp;
    private String newPassword;
}
