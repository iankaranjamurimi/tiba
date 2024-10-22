package com.tiba.tiba.Controllers;


import com.tiba.tiba.DTO.LogInDTO;
import com.tiba.tiba.DTO.UserLogInResponseDTO;
import com.tiba.tiba.Entities.User;


import com.tiba.tiba.Repositories.UserRepository;

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


    // Constructor
    public LogInController(UserRepository userRepository) {
        this.userRepository = userRepository; //this.passwordEncoder = passwordEncoder;

    }

    @PostMapping("/logIn")

    public ResponseEntity<LogInResponseController<UserLogInResponseDTO>> logIn(@RequestBody LogInDTO request) {
        // Validate request
        if (!StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getToken())) {
            return ResponseEntity.badRequest()
                    .body(LogInResponseController.error("Email and password are required"));
        }

// public ResponseEntity<String> logIn(@RequestBody LogInDTO request) {
// if (!StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getPassword())) {
// return ResponseEntity.badRequest().body("Email and password are required");
// }


        // Find user
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(LogInResponseController.error("User not found"));
        }
// Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
// if (existingUser.isEmpty()) {
// return ResponseEntity.badRequest().body("User not found");
//  }

//    return ResponseEntity.ok("logged in successfully");
//}


        // Convert User to UserResponseDTO
        UserLogInResponseDTO userResponse = convertToDTO(existingUser.get());

        // Return successful response
        return ResponseEntity.ok(LogInResponseController.success("Logged in successfully", userResponse));
    }
    private UserLogInResponseDTO convertToDTO(User user) {
        UserLogInResponseDTO dto = new UserLogInResponseDTO();
        dto.setId(Long.valueOf(user.getId())); // Convert Integer to Long if needed
        dto.setToken(user.getPassword());
//        dto.setEmail(user.getEmail());
//        dto.setFirstName(user.getFirstName());
//        dto.setMiddleName(user.getMiddleName());
//        dto.setLastName(user.getLastName());
        return dto;
    }
}


