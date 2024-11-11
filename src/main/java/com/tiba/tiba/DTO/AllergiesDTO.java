package com.tiba.tiba.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class AllergiesDTO {
    private String allergen;
    private String reaction_type;
    private String severity;
    private Date date;
    private String notes;
    private Long userId;
}

