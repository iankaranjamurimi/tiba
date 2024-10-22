package com.tiba.tiba.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@AllArgsConstructor
public class LogInDTO {

    private String email;
    private String Token;


}
