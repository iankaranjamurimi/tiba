package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.SignUpDTO;

import com.tiba.tiba.Services.SignUpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Valid
@RestController
//http://localhost:8080/tiba/auth/signup
@RequestMapping("/api/open")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpDTO request) {
        try {
            signUpService.registerUser(request);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}