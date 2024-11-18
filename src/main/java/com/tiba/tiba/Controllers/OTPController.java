package com.tiba.tiba.Controllers;

import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.OTPRepository;
import com.tiba.tiba.Repositories.UserRepository;
import com.tiba.tiba.Services.OTPService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/otp")
public class OTPController {

    private final OTPService otpService;
    private final UserRepository userRepository;

    public OTPController(OTPService otpService, UserRepository userRepository) {
        this.otpService = otpService;
        this.userRepository = userRepository;
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

    @PostMapping("/verify/{email}/{otp}")
    public ResponseEntity<String> verifyOTP(@PathVariable String email, @PathVariable String otp) {
        try {
            // Use existing service method to verify OTP
            boolean isValid = otpService.verifyOTP(email, otp);

            if (isValid) {
                return ResponseEntity.ok("OTP verified successfully");
            } else {
                return ResponseEntity.badRequest().body("Invalid or expired OTP");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Verification failed: " + e.getMessage());
        }
    }
}