package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.HospitalAdminDTO;
import com.tiba.tiba.Models.ApiResponse;
import com.tiba.tiba.Services.HospitalAdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@Valid
@RestController
@RequestMapping("/api/open")
@Data
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class HospitalAdminController {

    @Autowired
    private HospitalAdminService hospitalAdminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/create/hospitaladmin")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody HospitalAdminDTO request) {
        try {
            // Hashing the password before passing it to service
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            request.setPassword(hashedPassword);

            hospitalAdminService.registerUser(request);

            ApiResponse response = new ApiResponse(200, "User registered successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse(400, e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


//    static
//    class ApiResponse {
//        private int status;
//        private String message;
//
//        public ApiResponse(int i, String message) {
//
//        }
    }












//    @PostMapping("/create/hospitaladmin")
//    public ResponseEntity<String> signup(@Valid @RequestBody HospitalAdminDTO request) {
//        try {
//            // Hashing the password before passing it to service
//            String hashedPassword = passwordEncoder.encode(request.getPassword());
//            request.setPassword(hashedPassword);
//
//            hospitalAdminService.registerUser(request);
//            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }








