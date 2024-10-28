package com.tiba.tiba.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInResponseDTO<T> {
    private static String loggedInSuccessfully;
    private boolean success;
    private String message;
    private T data;


    public static <T> LogInResponseDTO<T> error(String message) {
        return new LogInResponseDTO<>(false, message, null);
    }

    public static Object success(String loggedInSuccessfully, UserLogInResponseDTO userResponse) {
        LogInResponseDTO.loggedInSuccessfully = loggedInSuccessfully;
        return new LogInResponseDTO<>(true, loggedInSuccessfully, userResponse);
    }
}
