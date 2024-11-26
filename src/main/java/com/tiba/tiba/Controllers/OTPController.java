package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.ForgotPasswordDTO;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.UserRepository;
import com.tiba.tiba.Services.OTPService;
import com.tiba.tiba.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
public class OTPController {

    private static final Logger log = LoggerFactory.getLogger(OTPController.class);
    private final OTPService otpService;
    private final UserRepository userRepository;
    private final UserService userService;

    public OTPController(OTPService otpService, UserRepository userRepository, UserService userService) {
        this.otpService = otpService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/generate/{email}")
    public ResponseEntity<String> generateOTP(@PathVariable String email) {
        try {
            // Check if user exists
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

            // Generate and send OTP using existing service method
            otpService.generateAndSendOTP(email);

            return ResponseEntity.ok("OTP has been sent to your email");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to process OTP request: " + e.getMessage());
        }
    }


    @PostMapping("/forgot/password")
    public ResponseEntity<?> resetPassword(@RequestBody ForgotPasswordDTO request) {
        try {
            // First verify the OTP
            boolean isOtpValid = otpService.verifyOTP(request.getEmail(), request.getOtp());

            if (!isOtpValid) {
                return ResponseEntity
                        .badRequest()
                        .body("Invalid or expired OTP");
            }

            // If OTP is valid, proceed with password reset
            userService.updatePassword(request.getEmail(), request.getNewPassword());

            return ResponseEntity
                    .ok()
                    .body("Password reset successful");

        } catch (Exception e) {
            log.error("Error in password reset: {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to reset password: " + e.getMessage());
        }
    }

}