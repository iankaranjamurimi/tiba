package com.tiba.tiba.Controllers;


import com.tiba.tiba.DTO.HospitalStaffDTO;
import com.tiba.tiba.Services.HospitalStaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")

public class HospitalStaffController {

    @Autowired
    private HospitalStaffService hospitalStaffService;

    @PostMapping("/create/hospitalstaff")
    public ResponseEntity<String> createHospitalStaff(@Valid @RequestBody HospitalStaffDTO hospitalStaffDTO) {
        try {
            hospitalStaffService.registerHospitalStaff(hospitalStaffDTO);
            return new ResponseEntity<>("Hospital Staff created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/hospitalstaff")
//    public ResponseEntity<HospitalStaffDTO> createHospitalStaff(@RequestBody HospitalStaffDTO hospitalStaffDTO) {
//        HospitalStaffDTO createdStaff = hospitalStaffService.createHospitalStaff(hospitalStaffDTO);
//        return ResponseEntity.ok(createdStaff);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalStaffDTO> getHospitalStaff(@PathVariable Integer id) {
        HospitalStaffDTO hospitalStaffDTO = hospitalStaffService.getHospitalStaff(id);
        if (hospitalStaffDTO != null) {
            return ResponseEntity.ok(hospitalStaffDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

