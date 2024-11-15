package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.UserOTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<UserOTP, Long> {
    Optional<UserOTP> findByUserEmailAndOtpAndIsUsedFalse(String email, String otp);
    void deleteByUserEmail(String email);
}
