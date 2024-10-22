package com.tiba.tiba.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> LoginResponseDTO<T> success(String message, T data) {
        return new LoginResponseDTO<>(true, message, data);
    }

    public static <T> LoginResponseDTO<T> error(String message) {
        return new LoginResponseDTO<>(false, message, null);
    }
}
