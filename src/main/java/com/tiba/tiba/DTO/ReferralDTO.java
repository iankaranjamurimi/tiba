package com.tiba.tiba.DTO;

import com.tiba.tiba.Entities.ReferralStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReferralDTO {
    private Long id;
    private Long referringHospitalStaffId;
    private Long referredHospitalStaffId;
    private Long patientId;
    private Long referringHospitalId;
    private Long referredHospitalId;
    private String reason;
    private ReferralStatus status;
    private LocalDateTime createdAt;
}
