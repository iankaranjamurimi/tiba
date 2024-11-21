package com.tiba.tiba.DTO;

import lombok.Data;
import java.util.List;

@Data
public class VitalsAllergiesDTO {
        private Double height;
        private Double weight;
        private String bpSystolic;
        private String bpDiastolic;
        private String bloodGroup;
        private List<String> allergies;
}
