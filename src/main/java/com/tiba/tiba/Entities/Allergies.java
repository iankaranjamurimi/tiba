package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Allergies {

    @Id
    @GeneratedValue
    private Long id;

    private String allergen;

    private String reaction_type;

    private String treatmentMedication;

    private String severity;//mild, moderate, severe

    private Date date;// diagnosed date

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
