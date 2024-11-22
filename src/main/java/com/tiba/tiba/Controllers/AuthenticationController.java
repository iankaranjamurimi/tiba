package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.LogInRequestDTO;
import com.tiba.tiba.DTO.LogInResponseDTO;
import com.tiba.tiba.Entities.HospitalAdmin;
import com.tiba.tiba.Entities.HospitalStaff;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.HospitalAdminRepository;
import com.tiba.tiba.Repositories.HospitalStaffRepository;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/open")
@Slf4j
public class AuthenticationController {
    private final UserSignUpRepository userSignUpRepository;
    private final HospitalAdminRepository hospitalAdminRepository;
    private final HospitalStaffRepository hospitalStaffRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationController(
            UserSignUpRepository userSignUpRepository,
            HospitalAdminRepository hospitalAdminRepository,
            HospitalStaffRepository hospitalStaffRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userSignUpRepository = userSignUpRepository;
        this.hospitalAdminRepository = hospitalAdminRepository;
        this.hospitalStaffRepository = hospitalStaffRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth/logIn")
    public ResponseEntity<LogInResponseDTO> login(@RequestBody LogInRequestDTO loginRequest) {
        log.info("Login attempt for email: {}", loginRequest.getEmail());

        // Validating the  input
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            log.warn("Login attempt with missing email or password");
            return ResponseEntity.badRequest().body(
                    LogInResponseDTO.builder()
                            .message("Email and password are required")
                            .build()
            );
        }

        // Finding  user by email
        Optional<User> userOptional = userSignUpRepository.findByEmail(loginRequest.getEmail());

        // Debug logging
        if (userOptional.isEmpty()) {
            log.error("No user found with email: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    LogInResponseDTO.builder()
                            .message("Invalid credentials")
                            .build()
            );
        }

        User user = userOptional.get();

        // Debug password check
        log.info("Stored password hash: {}", user.getPassword());
        log.info("Entered password matches: {}",
                passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()));

        // Check password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.warn("Invalid password attempt for email: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    LogInResponseDTO.builder()
                            .message("Invalid credentials")
                            .build()
            );
        }

        // Determine hospital name and role
        String hospitalName = determineHospitalName(user);
        String userRole = determineUserRole(user);

        // Build successful response
        LogInResponseDTO response = LogInResponseDTO.builder()
                .userId(user.getId())
                .role(userRole)
                .hospitalName(hospitalName)
                .message("Login successful")
                .build();

        log.info("Successful login for user: {}", loginRequest.getEmail());
        return ResponseEntity.ok(response);
    }

    private String determineHospitalName(User user) {
        // Check if user is HospitalAdmin
        Optional<HospitalAdmin> hospitalAdmin = hospitalAdminRepository.findByUser(user);
        if (hospitalAdmin.isPresent()) {
            return hospitalAdmin.get().getHospital().getHospitalName();
        }

        // Check if user is HospitalStaff
        Optional<HospitalStaff> hospitalStaff = hospitalStaffRepository.findByUser(user);
        return hospitalStaff.map(HospitalStaff::getHospitalName).orElse(null);
    }

    private String determineUserRole(User user) {
        return user.getRoles() != null ? user.getRoles().toString() : null;
    }
}