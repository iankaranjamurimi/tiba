package com.tiba.tiba.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserLogInResponseDTO {
    private Long id;
    private String token;

}

