package com.tiba.tiba.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDTO<T> {
    private static String signedInSuccessfully;
    private boolean success;
    private String message;
    private T data;


    public static <T> SignUpResponseDTO<T> error(String message) {
        return new SignUpResponseDTO<>(false, message, null);
    }

    public static Object success(String signedInSuccessfully, UserSignUpResponseDTO userResponse) {
        SignUpResponseDTO.signedInSuccessfully = signedInSuccessfully;
        return new SignUpResponseDTO<>(true, signedInSuccessfully, userResponse);
    }
}
