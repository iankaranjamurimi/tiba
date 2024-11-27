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

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> generateOTP(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Check if user exists
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

            // Generate and send OTP using existing service method
            otpService.generateAndSendOTP(email);

            response.put("success", true);
            response.put("message", "OTP has been sent to your email");
            response.put("data", null);  // or any relevant data you want to return
            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to process OTP request");
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/forgot/password")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody ForgotPasswordDTO request) {
        Map<String, Object> response = new HashMap<>();
        try {
            // First verify the OTP
            boolean isOtpValid = otpService.verifyOTP(request.getEmail(), request.getOtp());

            if (!isOtpValid) {
                response.put("success", false);
                response.put("message", "Invalid or expired OTP");
                response.put("error", "Invalid OTP");
                return ResponseEntity.badRequest().body(response);
            }

            // If OTP is valid, proceed with password reset
            userService.updatePassword(request.getEmail(), request.getNewPassword());

            response.put("success", true);
            response.put("message", "Password reset successful");
            response.put("data", null);  // or any relevant data you want to return
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error in password reset: {}", e.getMessage());
            response.put("success", false);
            response.put("message", "Failed to reset password");
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}