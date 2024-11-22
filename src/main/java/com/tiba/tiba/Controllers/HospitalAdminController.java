package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.HospitalAdminDTO;
import com.tiba.tiba.Models.ApiResponse;
import com.tiba.tiba.Services.HospitalAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HospitalAdminController {

    private final HospitalAdminService hospitalAdminService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/create/hospitaladmin")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody HospitalAdminDTO request) {
        try {
            // Validate password
            if (request.getPassword() == null || request.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        ApiResponse.badRequest("Password cannot be empty")
                );
            }

            // Hashing the password before passing it to service
            String hashedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            request.setPassword(hashedPassword);

            // Attempt to register the user
            HospitalAdminDTO registeredUser = hospitalAdminService.registerUser(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.ok("Hospital Admin registered successfully", registeredUser)
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

    @DeleteMapping("/hospitaladmin/{id}")
    public ResponseEntity<ApiResponse> deleteAdmin(@PathVariable Long id) {
        try {
            hospitalAdminService.deleteAdmin(id);
            return ResponseEntity.ok(
                    ApiResponse.ok("Hospital admin deleted successfully")
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

//    @GetMapping("/hospitaladmin/{id}")
//    public ResponseEntity<ApiResponse> getAdminById(@PathVariable Long id) {
//        try {
//            HospitalAdminDTO admin = hospitalAdminService.getAdminById(id);
//            if (admin == null) {
//                return ResponseEntity.status(404).body(
//                        ApiResponse.notFound("Hospital admin not found")
//                );
//            }
//            return ResponseEntity.ok(
//                    ApiResponse.ok("Hospital admin retrieved successfully", admin)
//            );
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(
//                    ApiResponse.badRequest(e.getMessage())
//            );
//        }
//    }


}

