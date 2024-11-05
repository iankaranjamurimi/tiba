package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.HospitalAdminDTO;
import com.tiba.tiba.Services.HospitalAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@Valid
@RestController
@RequestMapping("/api/open")
public class HospitalAdminController {

    @Autowired
    private HospitalAdminService hospitalAdminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/create/hospitaladmin")
        public ResponseEntity<String> signup(@Valid @RequestBody HospitalAdminDTO request) {
            try {
                // Hashing the password before passing it to service
                String hashedPassword = passwordEncoder.encode(request.getPassword());
                request.setPassword(hashedPassword);

                hospitalAdminService.registerUser(request);
                return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

}


