package com.tiba.tiba.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class OTPService {
    private static final Logger log = LoggerFactory.getLogger(OTPService.class);

    private final OTPRepository otpRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Transactional
    public void generateAndSendOTP(String email) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                throw new RuntimeException("User not found with email: " + email);
            }
            User user = userOptional.get();

            // Find existing OTP or create new one
            UserOTP userOTP = otpRepository.findByUser(user)
                    .orElse(new UserOTP());

            // Update the OTP
            String otp = generateOTP();
            userOTP.setUser(user);
            userOTP.setOtp(otp);
            userOTP.setExpiryTime(LocalDateTime.now().plusMinutes(5));
            userOTP.setUsed(false);
            userOTP.setVerified(false);

            otpRepository.save(userOTP);
            sendOTPEmail(user, otp);

        } catch (Exception e) {
            log.error("Error in generateAndSendOTP: {}", e.getMessage());
            throw new RuntimeException("Failed to process OTP request: " + e.getMessage());
        }
    }

    public String generateOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void sendOTPEmail(User user, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("iankaranja420@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Your TIBA Verification Code");

        // Replace String template with traditional string concatenation
        String emailText = String.format(
                "Dear %s,\n\n" +
                        "Your verification code is: %s\n" +
                        "This code will expire in 5 minutes.\n\n" +
                        "If you didn't request this code, please ignore this email.\n\n" +
                        "Best regards,\n" +
                        "TIBA",
                user.getFirstName(), otp);

        message.setText(emailText);

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to send email to {}: {}", user.getEmail(), e.getMessage());
            throw e;
        }
    }

    @Transactional
    public boolean verifyOTP(String email, String otp) {
        try {
            // Validate inputs
            if (email == null || email.trim().isEmpty() || otp == null || otp.trim().isEmpty()) {
                log.warn("Invalid email or OTP input");
                return false;
            }

            Optional<UserOTP> userOTP = otpRepository.findByUserEmailAndOtpAndIsUsedFalse(email, otp);

            if (userOTP.isPresent()) {
                UserOTP otpEntity = userOTP.get();

                // Check if OTP is expired
                if (LocalDateTime.now().isAfter(otpEntity.getExpiryTime())) {
                    log.info("OTP expired for email: {}", email);
                    return false;
                }

                // Mark OTP as used and verified
                updateOTPStatus(otpEntity);

                log.info("OTP verified successfully for email: {}", email);
                return true;
            }

            log.info("Invalid OTP attempt for email: {}", email);
            return false;

        } catch (Exception e) {
            log.error("Error in verifyOTP: {}", e.getMessage());
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



//package com.tiba.tiba.Services;
//
//import jakarta.transaction.Transactional;
//import org.springframework.mail.MailException;
//import org.springframework.stereotype.Service;
//import com.tiba.tiba.Entities.User;
//import com.tiba.tiba.Entities.UserOTP;
//import com.tiba.tiba.Repositories.OTPRepository;
//import com.tiba.tiba.Repositories.UserRepository;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.Random;
//
//@Service
//public class OTPService {
//    private static final Logger log = LoggerFactory.getLogger(OTPService.class);
//
//    private final OTPRepository otpRepository;
//    private final UserRepository userRepository;
//    private final JavaMailSender javaMailSender;
//
//    public OTPService(OTPRepository otpRepository, UserRepository userRepository, JavaMailSender javaMailSender) {
//        this.otpRepository = otpRepository;
//        this.userRepository = userRepository;
//        this.javaMailSender = javaMailSender;
//    }
//
//    @Transactional
//    public void generateAndSendOTP(String email) {
//        try {
//            Optional<User> userOptional = userRepository.findByEmail(email);
//            if (userOptional.isEmpty()) {
//                throw new RuntimeException("User not found with email: " + email);
//            }
//            User user = userOptional.get();
//
//            // Find existing OTP or create new one
//            UserOTP userOTP = otpRepository.findByUser(user)
//                    .orElse(new UserOTP());
//
//            // Update the OTP
//            String otp = generateOTP();
//            userOTP.setUser(user);
//            userOTP.setOtp(otp);
//            userOTP.setExpiryTime(LocalDateTime.now().plusMinutes(5));
//            userOTP.setUsed(false);
//            userOTP.setVerified(false);
//
//            otpRepository.save(userOTP);
//            sendOTPEmail(user, otp);
//
//        } catch (Exception e) {
//            log.error("Error in generateAndSendOTP: {}", e.getMessage());
//            throw new RuntimeException("Failed to process OTP request: " + e.getMessage());
//        }
//    }
//
//    public String generateOTP() {
//        return String.format("%06d", new Random().nextInt(999999));
//    }
//
//    private void sendOTPEmail(User user, String otp) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("iankaranja420@gmail.com");
//        message.setTo(user.getEmail());
//        message.setSubject("Your TIBA Verification Code");
//        message.setText(STR."""
//            Dear \{user.getFirstName()},
//
//            Your verification code is: \{otp}
//            This code will expire in 5 minutes.
//
//            If you didn't request this code, please ignore this email.
//
//            Best regards,
//            TIBA
//            """);
//
//        try {
//            javaMailSender.send(message);
//        } catch (MailException e) {
//            log.error("Failed to send email to {}: {}", user.getEmail(), e.getMessage());
//            throw e;
//        }
//    }
//
//    @Transactional
//    public boolean verifyOTP(String email, String otp) {
//        try {
//            // Validate inputs
//            if (email == null || email.trim().isEmpty() || otp == null || otp.trim().isEmpty()) {
//                log.warn("Invalid email or OTP input");
//                return false;
//            }
//
//            Optional<UserOTP> userOTP = otpRepository.findByUserEmailAndOtpAndIsUsedFalse(email, otp);
//
//            if (userOTP.isPresent()) {
//                UserOTP otpEntity = userOTP.get();
//
//                // Check if OTP is expired
//                if (LocalDateTime.now().isAfter(otpEntity.getExpiryTime())) {
//                    log.info("OTP expired for email: {}", email);
//                    return false;
//                }
//
//                // Mark OTP as used and verified
//                updateOTPStatus(otpEntity);
//
//                log.info("OTP verified successfully for email: {}", email);
//                return true;
//            }
//
//            log.info("Invalid OTP attempt for email: {}", email);
//            return false;
//
//        } catch (Exception e) {
//            log.error("Error in verifyOTP: {}", e.getMessage());
//            throw new RuntimeException("Failed to verify OTP: " + e.getMessage());
//        }
//    }
//
//    private void updateOTPStatus(UserOTP otpEntity) {
//        otpEntity.setUsed(true);
//        otpEntity.setVerified(true);
//        otpRepository.save(otpEntity);
//
//        // Update user verification status if needed
//        User user = otpEntity.getUser();
//        userRepository.save(user);
//    }
//}
