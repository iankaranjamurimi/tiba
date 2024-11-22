package com.tiba.tiba.DTO;

import com.tiba.tiba.Entities.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminStaffLogInResponseDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String token;
    private UserRole roles;
    private String hospitalName;
}