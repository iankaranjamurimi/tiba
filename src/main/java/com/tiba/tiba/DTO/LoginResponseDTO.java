package com.tiba.tiba.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO<T> {
    private static String loggedInSuccessfully;
    private boolean success;
    private String message;
    private T data;


    public static <T> LoginResponseDTO<T> error(String message) {
        return new LoginResponseDTO<>(false, message, null);
    }

    public static Object success(String loggedInSuccessfully, UserLogInResponseDTO userResponse) {
        LoginResponseDTO.loggedInSuccessfully = loggedInSuccessfully;
        return new LoginResponseDTO<>(true, loggedInSuccessfully, userResponse);
    }
}
