package com.tiba.tiba.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicalRecordsUpdateDTO {
        private String notes;
        private String diagnosis;
        private String treatment;
        private LocalDate submittedAt;
        private String submittedBy;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private Long userId;

        
}
