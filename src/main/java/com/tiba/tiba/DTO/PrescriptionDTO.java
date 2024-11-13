package com.tiba.tiba.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

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
    private LocalDate prescribedDate;
    private String status;
    private String notes;

    @JsonIgnore
    private Long userId;

    @JsonIgnore
    private Long hospitalStaffId;

}
