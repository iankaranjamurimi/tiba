package com.tiba.tiba.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tiba.tiba.Entities.PrescriptionStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PrescriptionDTO {
    private String dosage;
    private String frequency;
    private String duration;
    private Integer quantity;
    private LocalDate prescribedDate;
    private PrescriptionStatus status;
    private String notes;
    // ie; fields for input only but are not included in the JSON response
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long hospitalStaffId;

}
