package com.tiba.tiba.DTO;


import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicalRecordsUpdateDTO {

        private String notes;
        private String diagnosis;
        private String treatment;
        private LocalDate submittedAt;
        private String submittedBy;
        private Long userId;

        
}
