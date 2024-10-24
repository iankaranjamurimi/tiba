package com.tiba.tiba.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Hospital {

    @Id
    @GeneratedValue
    private Integer id;

    private String hospitalName;

    private String hospitalAddress;

    private String hospitalLocation;

    private String hospitalContactNumber;

    private String hospitalEmail;


}
