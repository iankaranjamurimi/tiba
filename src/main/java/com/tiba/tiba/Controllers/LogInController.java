package com.tiba.tiba.Controllers;


import com.tiba.tiba.DTO.UserLogInDTO;
import com.tiba.tiba.DTO.SignUpResponseDTO;
import com.tiba.tiba.DTO.UserLogInResponseDTO;
import com.tiba.tiba.Entities.User;


import com.tiba.tiba.Repositories.UserSignUpRepository;

import com.tiba.tiba.Services.JwtUtil;
import com.tiba.tiba.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//http://localhost:8080/tiba/auth/logIn
//https://tiba.onrender.com/swagger-ui/index.html#/log-in-controller/logIn
@RestController
@RequestMapping("/api/open/")
public class LogInController {

    @Autowired
    private UserSignUpRepository userSignUpRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Constructor
    public LogInController(UserSignUpRepository userSignUpRepository) {
        this.userSignUpRepository = userSignUpRepository;

    }

    @PostMapping("/user/logIn")

    public ResponseEntity<SignUpResponseDTO<UserLogInResponseDTO>> logIn(@RequestBody UserLogInDTO request) {
        // Validate request
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
        if (!userService.verifyPassword(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(SignUpResponseDTO.error("Invalid password"));
        }

        // Convert User to UserResponseDTO
        UserLogInResponseDTO userResponse = convertToDTO(existingUser.get());

        // successful log in response
        return ResponseEntity.ok().body(new SignUpResponseDTO<>(true, "Logged in successfully", userResponse));
    }

    private UserLogInResponseDTO convertToDTO(User user) {
        UserLogInResponseDTO dto = new UserLogInResponseDTO();
        dto.setId(user.getId());
        dto.setToken(jwtUtil.generateToken(user.getEmail(), user.getRoles()));
        dto.setRoles(user.getRoles());
        return dto;
    }

}

