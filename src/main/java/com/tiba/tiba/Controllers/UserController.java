package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.LogInDTO;
import com.tiba.tiba.DTO.SignUpDTO;
import com.tiba.tiba.Repositories.PatientRepository;
import com.tiba.tiba.Repositories.UserRepository;
import com.tiba.tiba.Services.PatientService;
import com.tiba.tiba.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

//http://localhost:8080/tiba/users/sign_up
@RestController
@RequestMapping("/tiba/users")
public class UserController {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    // Constructor
    public UserController(UserRepository userRepository,/*PasswordEncoder passwordEncoder*/ PatientRepository patientRepository, PatientService patientService)
     {
        this.userRepository = userRepository; //this.passwordEncoder = passwordEncoder;
       this.userService = new UserService(userRepository);


    }

    @PostMapping("/sign_up")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpDTO request) {
        if (!StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getPassword()) ||
                !StringUtils.hasText(request.getFirstname()) || !StringUtils.hasText(request.getLastname())){
            return ResponseEntity.badRequest().body("All fields are required");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User with given email already exists");
        }

        // Create a new User instance and populate it with data from the DTO
        com.tiba.tiba.Entities.User user = new com.tiba.tiba.Entities.User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Hash the password before saving the user
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());



        // Save the user to the repository
        com.tiba.tiba.Entities.User createdUser = userService.sign_upNewUser(user);
        return ResponseEntity.ok("User registered successfully");
        
    }

    @PostMapping("/log_in")
    public ResponseEntity<String> logIn(@RequestBody LogInDTO request) {
        if (!StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getPassword())) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        Optional<com.tiba.tiba.Entities.User> existingUser = userRepository.findByEmail(request.getEmail());
        if (!existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }

       /* if (!passwordEncoder.matches(request.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");

         }*/
        return ResponseEntity.ok("logged in successfully");
    }
}