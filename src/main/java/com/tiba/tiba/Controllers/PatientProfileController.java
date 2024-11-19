package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.PatientProfileDTO;
import com.tiba.tiba.Services.PatientProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
public class PatientProfileController {

     private final PatientProfileService patientProfileService;

    public PatientProfileController(PatientProfileService patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    @GetMapping("/g/patient/profile/{userId}")
    public ResponseEntity<PatientProfileDTO> getPatientProfile(@PathVariable Long userId) {
        PatientProfileDTO profile = patientProfileService.getPatientProfile(userId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/patient/profile/{userId}")
    public ResponseEntity<Void> updatePatientProfile(
            @PathVariable Long userId,
            @RequestBody PatientProfileDTO profileDTO) {
        patientProfileService.updatePatientProfile(userId, profileDTO);
        return ResponseEntity.ok().build();
    }
}
