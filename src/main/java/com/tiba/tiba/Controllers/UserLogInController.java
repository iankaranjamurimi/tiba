package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.UserLogInDTO;
import com.tiba.tiba.DTO.SignUpResponseDTO;
import com.tiba.tiba.DTO.PatientLogInResponseDTO;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import com.tiba.tiba.Services.HospitalAdminService;
import com.tiba.tiba.Services.JwtUtil;
import com.tiba.tiba.Services.UserService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/open/")
@CrossOrigin(origins = "*")
public class UserLogInController {

    private final UserSignUpRepository userSignUpRepository;
    private final UserService userService;

    @Getter
    private final HospitalAdminService hospitalAdminService;
    private final JwtUtil jwtUtil;

    public UserLogInController(UserSignUpRepository userSignUpRepository, UserService userService, HospitalAdminService hospitalAdminService, JwtUtil jwtUtil) {
        this.userSignUpRepository = userSignUpRepository;
        this.userService = userService;
        this.hospitalAdminService = hospitalAdminService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/user/logIn")
    public ResponseEntity<SignUpResponseDTO<PatientLogInResponseDTO>> logIn(@RequestBody UserLogInDTO request) {
        // Validation request
        if (!StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(SignUpResponseDTO.error("Email and password are required"));
        }

        // Find user
        Optional<User> existingUser = userSignUpRepository.findByEmail(request.getEmail());
        if (existingUser.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(SignUpResponseDTO.error("User not found"));
        }

        // Validate password
        User user = existingUser.get();
        if (userService.verifyPassword(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(SignUpResponseDTO.error("Invalid password"));
        }

        // Convert User to UserResponseDTO
        PatientLogInResponseDTO userResponse = convertToDTO(existingUser.get());

        // successful log in response
        return ResponseEntity.ok().body(new SignUpResponseDTO<>(true, "Logged in successfully", userResponse));
    }

    private PatientLogInResponseDTO convertToDTO(User user) {
        PatientLogInResponseDTO dto = new PatientLogInResponseDTO();
        dto.setId(user.getId());
        dto.setToken(jwtUtil.generateToken(user.getEmail(), user.getRoles()));
        dto.setRoles(user.getRoles());
        return dto;
    }

}

