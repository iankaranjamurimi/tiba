package com.tiba.tiba.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;
    private int statusCode;
    private Object data;

    // Constructor for simple success/failure responses
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.statusCode = success ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
    }

    // Constructor for responses with status code
    public ApiResponse(boolean success, String message, HttpStatus status) {
        this.success = success;
        this.message = message;
        this.statusCode = status.value();
    }

    // Constructor for responses with data
    public ApiResponse(boolean success, String message, HttpStatus status, Object data) {
        this.success = success;
        this.message = message;
        this.statusCode = status.value();
        this.data = data;
    }

    // Static factory methods for common responses
    public static ApiResponse ok(String message) {
        return new ApiResponse(true, message, HttpStatus.OK);
    }

    public static ApiResponse ok(String message, Object data) {
        return new ApiResponse(true, message, HttpStatus.OK, data);
    }

    public static ApiResponse badRequest(String message) {
        return new ApiResponse(false, message, HttpStatus.BAD_REQUEST);
    }

    public static ApiResponse notFound(String message) {
        return new ApiResponse(false, message, HttpStatus.NOT_FOUND);
    }
}