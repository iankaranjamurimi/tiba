package com.tiba.tiba.Controllers;


import com.tiba.tiba.DTO.LogInDTO;
import com.tiba.tiba.Entities.User;

import com.tiba.tiba.Repositories.PatientRepository;
import com.tiba.tiba.Repositories.UserRepository;
import com.tiba.tiba.Services.PatientService;
import com.tiba.tiba.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//http://localhost:8080/tiba/auth/log_in
@RestController
@RequestMapping("/tiba/auth")
public class LogInController {

    @Autowired

    private UserRepository userRepository;
    private UserService userService;


    // Constructor
    public LogInController(UserRepository userRepository,PatientRepository patientRepository, PatientService patientService)
    {
        this.userRepository = userRepository; //this.passwordEncoder = passwordEncoder;
        this.userService = new UserService(userRepository);


    }

    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestBody LogInDTO request) {
        if (!StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getPassword())) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (!existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        return ResponseEntity.ok("logged in successfully");
    }

}
