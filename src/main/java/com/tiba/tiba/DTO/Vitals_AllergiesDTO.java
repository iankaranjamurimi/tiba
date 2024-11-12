package com.tiba.tiba.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Vitals_AllergiesDTO {

        private Double height;
        private Double weight;
        private String bpSystolic;
        private String bpDiastolic;
        private String bloodGroup;
        private List<String> allergies;
}
