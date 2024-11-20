package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.HospitalAdminDTO;
import com.tiba.tiba.Models.ApiResponse;
import com.tiba.tiba.Services.HospitalAdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
@Data
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class HospitalAdminController {

    private final HospitalAdminService hospitalAdminService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/create/hospitaladmin")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody HospitalAdminDTO request) {
        try {
            // Hashing the password before passing it to service
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            request.setPassword(hashedPassword);

            hospitalAdminService.registerUser(request);

            return ResponseEntity.ok(
                    ApiResponse.ok("User registered successfully", request)
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }}

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
//
//    @DeleteMapping("/hospitaladmin/{id}")
//    public ResponseEntity<ApiResponse> deleteAdmin(@PathVariable Long id) {
//        try {
//            hospitalAdminService.deleteAdmin(id);
//            return ResponseEntity.ok(
//                    ApiResponse.ok("Hospital admin deleted successfully")
//            );
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(
//                    ApiResponse.badRequest(e.getMessage())
//            );
//        }
//    }
