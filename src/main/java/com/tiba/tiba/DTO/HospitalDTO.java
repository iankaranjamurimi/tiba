package com.tiba.tiba.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class HospitalDTO {

    private Integer id;

    private String hospitalName;

    private String hospitalAddress;

    private String hospitalLocation;

    private String hospitalContactNumber;

    private Integer HospitalAdmin_id;


}
