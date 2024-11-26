package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Entities.UserOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<UserOTP, Long> {
    Optional<UserOTP> findByUserEmailAndOtpAndIsUsedFalse(String email, String otp);
    Optional<UserOTP> findByUser(User user);
}
