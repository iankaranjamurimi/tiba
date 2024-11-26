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

}
