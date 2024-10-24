package com.tiba.tiba.Controllers;


import com.tiba.tiba.DTO.LogInDTO;
import com.tiba.tiba.DTO.LoginResponseDTO;
import com.tiba.tiba.DTO.UserLogInResponseDTO;
import com.tiba.tiba.Entities.User;


import com.tiba.tiba.Repositories.UserRepository;

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
    private UserRepository userRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Constructor
    public LogInController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @PostMapping("/logIn")

    public ResponseEntity<LoginResponseDTO<UserLogInResponseDTO>> logIn(@RequestBody LogInDTO request) {
        // Validate request
        if (!StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(LoginResponseDTO.error("Email and password are required"));
        }

        // Find user
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(LoginResponseDTO.error("User not found"));
        }

        // Convert User to UserResponseDTO
        UserLogInResponseDTO userResponse = convertToDTO(existingUser.get());

        // successful log in response
        return ResponseEntity.ok().body(new LoginResponseDTO<>(true, "Logged in successfully", userResponse));
    }
    private UserLogInResponseDTO convertToDTO(User user) {
        UserLogInResponseDTO dto = new UserLogInResponseDTO();
        dto.setId(Long.valueOf(user.getId()));
        dto.setToken(jwtUtil.generateToken(user.getEmail(), user.getRoles()));
        dto.setRoles(user.getRoles());
        return dto;
    }
}


