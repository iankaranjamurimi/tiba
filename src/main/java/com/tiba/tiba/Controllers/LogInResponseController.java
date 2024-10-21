package com.tiba.tiba.Controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInResponseController<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> LogInResponseController<T> success(String message, T data) {
        return new LogInResponseController<>(true, message, data);
    }

    public static <T> LogInResponseController<T> error(String message) {
        return new LogInResponseController<>(false, message, null);
    }
}
