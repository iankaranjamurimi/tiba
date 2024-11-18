package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOTP {
    @Id
    @GeneratedValue
    private Long id;
    private String otp;
    private LocalDateTime expiryTime;
    private boolean isUsed;
    private boolean isVerified;

    @OneToOne
    private User user;

}
