package com.tiba.tiba.Models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(int i, String message) {
        this.success = true;
    }
}




