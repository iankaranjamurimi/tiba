package com.tiba.tiba.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AppointmentsDTO {
    private Long appointmentsId;
    private Long hospitalStaffId;
    private Long patientId;
    private String appointmentType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String notes;
    private LocalDateTime createdAt;


}


