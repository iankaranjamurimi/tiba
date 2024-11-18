package com.tiba.tiba.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Entities.UserOTP;
import com.tiba.tiba.Repositories.OTPRepository;
import com.tiba.tiba.Repositories.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OTPService {

    private static final Logger logger = LoggerFactory.getLogger(OTPService.class);

    private final OTPRepository otpRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public OTPService(OTPRepository otpRepository, UserRepository userRepository, JavaMailSender javaMailSender) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }
    @Transactional
    public void generateAndSendOTP(String email) {
        try {
            // Validate email
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }

            // Find user and handle casting properly
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                throw new RuntimeException("User not found with email: " + email);
            }
            User user = userOptional.get();

            try {
                // Delete any existing unused OTPs
                otpRepository.deleteByUserEmail(email);

                // Generate 6-digit OTP
                String otp = generateOTP();

                // Save OTP to database
                UserOTP userOTP = createUserOTP(user, otp);
                otpRepository.save(userOTP);

                // Send OTP via email
                sendOTPEmail(user, otp);

                logger.info("OTP generated and sent successfully for email: {}", email);

            } catch (MailException e) {
                logger.error("Failed to send OTP email: {}", e.getMessage());
                throw new RuntimeException("Failed to send OTP email. Please try again later.", e);
            } catch (Exception e) {
                logger.error("Error in OTP generation process: {}", e.getMessage());
                throw new RuntimeException("Error in OTP generation process. Please try again.", e);
            }

        } catch (Exception e) {
            logger.error("Error in generateAndSendOTP: {}", e.getMessage());
            throw new RuntimeException("Failed to process OTP request: " + e.getMessage());
        }
    }

    public String generateOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private UserOTP createUserOTP(User user, String otp) {
        UserOTP userOTP = new UserOTP();
        userOTP.setUser(user);
        userOTP.setOtp(otp);
        userOTP.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        userOTP.setUsed(false);
        userOTP.setVerified(false);
        return userOTP;
    }

    private void sendOTPEmail(User user, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("iankaranja420@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Your TIBA Verification Code");
        message.setText(STR."""
            Dear \{user.getFirstName()},

            Your verification code is: \{otp}
            This code will expire in 5 minutes.

            If you didn't request this code, please ignore this email.

            Best regards,
            TIBA Team
            """);

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            logger.error("Failed to send email to {}: {}", user.getEmail(), e.getMessage());
            throw e;
        }
    }

    @Transactional
    public boolean verifyOTP(String email, String otp) {
        try {
            // Validate inputs
            if (email == null || email.trim().isEmpty() || otp == null || otp.trim().isEmpty()) {
                logger.warn("Invalid email or OTP input");
                return false;
            }

            Optional<UserOTP> userOTP = otpRepository.findByUserEmailAndOtpAndIsUsedFalse(email, otp);

            if (userOTP.isPresent()) {
                UserOTP otpEntity = userOTP.get();

                // Check if OTP is expired
                if (LocalDateTime.now().isAfter(otpEntity.getExpiryTime())) {
                    logger.info("OTP expired for email: {}", email);
                    return false;
                }

                // Mark OTP as used and verified
                updateOTPStatus(otpEntity);

                logger.info("OTP verified successfully for email: {}", email);
                return true;
            }

            logger.info("Invalid OTP attempt for email: {}", email);
            return false;

        } catch (Exception e) {
            logger.error("Error in verifyOTP: {}", e.getMessage());
            throw new RuntimeException("Failed to verify OTP: " + e.getMessage());
        }
    }

    private void updateOTPStatus(UserOTP otpEntity) {
        otpEntity.setUsed(true);
        otpEntity.setVerified(true);
        otpRepository.save(otpEntity);

        // Update user verification status if needed
        User user = otpEntity.getUser();
        userRepository.save(user);
    }
}