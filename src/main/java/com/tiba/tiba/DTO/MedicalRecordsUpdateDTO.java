package com.tiba.tiba.DTO;


import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicalRecordsUpdateDTO {

        private String notes;
        private String diagnosis;
        private String treatment;
//        private String medication;
        private LocalDate submittedAt;
        private String submittedBy;
//        private Boolean followUpRequired;
//        private LocalDate followUpDate;
        private Long patientId;
        private Long hospitalStaffId;

        
}
