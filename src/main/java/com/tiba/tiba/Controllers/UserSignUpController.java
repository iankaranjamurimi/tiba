package com.tiba.tiba.Controllers;
import com.tiba.tiba.DTO.UserSignUpResponseDTO;
import com.tiba.tiba.DTO.UserSignUpDTO;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Services.PatientService;
import com.tiba.tiba.Services.UserService;
import com.tiba.tiba.Services.UserSignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Valid
@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserSignUpController {

    private final UserSignUpService userSignUpService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final PatientService patientService;


    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDTO> registerUser(@Valid @RequestBody UserSignUpDTO request) {
        try {
            String hashedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            request.setPassword(hashedPassword);

            userSignUpService.registerUser(request);

            return new ResponseEntity<>(new UserSignUpResponseDTO("signed in successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserSignUpResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/firstname/check/{idNumber}")
    public ResponseEntity<Map<String, Object>> getPatientFirstNameIfExists(@PathVariable String idNumber) {
        Optional<Patient> patient = patientService.getPatientByIdNumber(idNumber);

        Map<String, Object> response = new HashMap<>();
        if (patient.isPresent()) {
            response.put("exists", true);
            response.put("firstName", patient.get().getUser().getFirstName());
            response.put("message", "Patient exists.");
            return ResponseEntity.ok(response);
        } else {
            response.put("exists", false);
            response.put("message", "Patient does not exist.");
            return ResponseEntity.status(404).body(response);
        }
    }


    @GetMapping("/AllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}