package com.tiba.tiba.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class PrescriptionDTO {

    private String dosage;
    private String frequency;
    private String duration;
    private Integer quantity;
//    private Integer refills;
    private LocalDate prescribedDate;
    private String status;
    private String notes;
//    private Long userId;
//    private Long hospitalStaffId;

}
