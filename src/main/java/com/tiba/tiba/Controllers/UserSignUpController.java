package com.tiba.tiba.Controllers;
import com.tiba.tiba.DTO.UserSignUpResponseDTO;
import com.tiba.tiba.DTO.UserSignUpDTO;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Services.UserSignUpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Valid
@RestController
//http://localhost:5050/api/open/signup
//http://localhost:5050/api/open/logIn
@RequestMapping("/api/open")
public class UserSignUpController {

    @Autowired
    private UserSignUpService userSignUpService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDTO> signup(@Valid @RequestBody UserSignUpDTO request) {
        try {
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            request.setPassword(hashedPassword);

            userSignUpService.registerUser(request);

            return new ResponseEntity<>(new UserSignUpResponseDTO("signed in successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserSignUpResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/all_users")
    public List<User> getAllUsers() {
        return userSignUpService.getAllUsers();
    }

}

