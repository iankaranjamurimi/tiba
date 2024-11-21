package com.tiba.tiba.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PrescriptionDTO {
    private String dosage;
    private String frequency;
    private String duration;
    private Integer quantity;
    private LocalDate prescribedDate;
    private String status;
    private String notes;

    // fields for input only but not included in JSON response
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long hospitalStaffId;

}
