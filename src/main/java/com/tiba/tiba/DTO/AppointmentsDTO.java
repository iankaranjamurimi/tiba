package com.tiba.tiba.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentsDTO {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;
    private String appointmentType;
    private LocalDateTime startTime;
    private String status;
    private LocalDate submittedAt;
    private String submittedBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long hospitalStaffId;
}


