package com.tiba.tiba.Controllers;
import com.tiba.tiba.DTO.UserSignUpResponseDTO;
import com.tiba.tiba.DTO.UserSignUpDTO;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Services.UserService;
import com.tiba.tiba.Services.UserSignUpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Valid
@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
public class UserSignUpController {

    private final UserSignUpService userSignUpService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    public UserSignUpController(UserSignUpService userSignUpService, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.userSignUpService = userSignUpService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDTO> signup(@Valid @RequestBody UserSignUpDTO request) {
        try {
            String hashedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            request.setPassword(hashedPassword);

            userSignUpService.registerUser(request);

            return new ResponseEntity<>(new UserSignUpResponseDTO("signed in successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserSignUpResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/AllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}

