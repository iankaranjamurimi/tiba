package com.tiba.tiba.Controllers;
import com.tiba.tiba.DTO.SignUpDTO;
import com.tiba.tiba.Services.SignUpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
@Valid
@RestController
//http://localhost:5050/api/open/signup
//http://localhost:5050/api/open/logIn
//https://tiba.onrender.com/swagger-ui/index.html#/log-in-controller/logIn
@RequestMapping("/api/open")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpDTO request) {
        try {
            // Hash password before passing to service
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            request.setPassword(hashedPassword);

            signUpService.registerUser(request);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}