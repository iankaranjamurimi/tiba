package com.tiba.tiba.Controllers;


import com.tiba.tiba.DTO.HospitalStaffDTO;
import com.tiba.tiba.Services.HospitalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospitalstaff")

public class HospitalStaffController {

    @Autowired
    private HospitalStaffService hospitalStaffService;

    @PostMapping
    public ResponseEntity<HospitalStaffDTO> createHospitalStaff(@RequestBody HospitalStaffDTO hospitalStaffDTO) {
        HospitalStaffDTO createdStaff = hospitalStaffService.createHospitalStaff(hospitalStaffDTO);
        return ResponseEntity.ok(createdStaff);
    }

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

