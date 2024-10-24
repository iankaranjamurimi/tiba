package com.tiba.tiba.DTO;

import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Entities.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class UserLogInResponseDTO {
    private Long id;
    private String token;
    //private String roles;
    private UserRole roles;



}

