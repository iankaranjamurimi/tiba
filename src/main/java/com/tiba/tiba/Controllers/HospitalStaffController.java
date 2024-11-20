package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.HospitalStaffDTO;
import com.tiba.tiba.Models.ApiResponse;
import com.tiba.tiba.Services.HospitalStaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
public class HospitalStaffController {

    @Autowired
    private HospitalStaffService hospitalStaffService;

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

    @PostMapping("/create/hospitalstaff")
    public ResponseEntity<ApiResponse> createHospitalStaff(@Valid @RequestBody HospitalStaffDTO hospitalStaffDTO) {
        try {

            hospitalStaffService.registerHospitalStaff(hospitalStaffDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse(true, "Hospital Staff created successfully", HttpStatus.CREATED, hospitalStaffDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

}

//@PutMapping("/hospitalstaff/{id}")
//public ResponseEntity<ApiResponse> updateHospitalStaff(
//        @PathVariable Integer id,
//        @Valid @RequestBody HospitalStaffDTO hospitalStaffDTO) {
//    try {
//        hospitalStaffDTO.setId(id);
//        HospitalStaffDTO updatedStaff = hospitalStaffService.updateHospitalStaff(hospitalStaffDTO);
//        return ResponseEntity.ok(
//                ApiResponse.ok("Hospital staff updated successfully", updatedStaff)
//        );
//    } catch (Exception e) {
//        return ResponseEntity.badRequest().body(
//                ApiResponse.badRequest(e.getMessage())
//        );
//    }
//}
//
//@DeleteMapping("/hospitalstaff/{id}")
//public ResponseEntity<ApiResponse> deleteHospitalStaff(@PathVariable Integer id) {
//    try {
//        hospitalStaffService.deleteHospitalStaff(id);
//        return ResponseEntity.ok(
//                ApiResponse.ok("Hospital staff deleted successfully")
//        );
//    } catch (Exception e) {
//        return ResponseEntity.badRequest().body(
//                ApiResponse.badRequest(e.getMessage())
//        );
//    }
//}