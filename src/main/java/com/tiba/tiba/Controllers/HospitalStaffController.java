package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.HospitalStaffDTO;
import com.tiba.tiba.Models.ApiResponse;
import com.tiba.tiba.Services.HospitalStaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class HospitalStaffController {

    private final HospitalStaffService hospitalStaffService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/create/hospitalstaff")
    public ResponseEntity<ApiResponse> createHospitalStaff(@Valid @RequestBody HospitalStaffDTO hospitalStaffDTO) {
        try {
            // Validate password
            if (hospitalStaffDTO.getPassword() == null || hospitalStaffDTO.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        ApiResponse.badRequest("Password cannot be empty")
                );
            }


            // Hash the password
            String hashedPassword = bCryptPasswordEncoder.encode(hospitalStaffDTO.getPassword());
            hospitalStaffDTO.setPassword(hashedPassword);

            // Register hospital staff
            HospitalStaffDTO registeredStaff = hospitalStaffService.registerHospitalStaff(hospitalStaffDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse(true, "Hospital Staff created successfully", HttpStatus.CREATED, registeredStaff)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

    @GetMapping("/hospitalstaff/{id}")
    public ResponseEntity<ApiResponse> getHospitalStaff(@PathVariable Integer id) {
        try {
            HospitalStaffDTO hospitalStaffDTO = hospitalStaffService.getHospitalStaff(id);
            if (hospitalStaffDTO != null) {
                return ResponseEntity.ok(
                        ApiResponse.ok("Hospital staff retrieved successfully", hospitalStaffDTO)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponse.notFound("Hospital staff not found")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

    @DeleteMapping("/hospitalstaff/{id}")
    public ResponseEntity<ApiResponse> deleteHospitalStaff(@PathVariable Integer id) {
        try {
            hospitalStaffService.deleteHospitalStaff(id);
            return ResponseEntity.ok(
                    ApiResponse.ok("Hospital staff deleted successfully")
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

}


