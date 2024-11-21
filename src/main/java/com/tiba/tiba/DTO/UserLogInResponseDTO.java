package com.tiba.tiba.DTO;

import com.tiba.tiba.Entities.UserRole;
import lombok.Data;

@Data
public class UserLogInResponseDTO {
    private Long id;
    private String token;
    private UserRole roles;
}

