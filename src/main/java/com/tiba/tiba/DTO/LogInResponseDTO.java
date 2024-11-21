package com.tiba.tiba.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogInResponseDTO {
    private Long userId;
    private String role;
    private String hospitalName;
    private String message;
}
