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

    @GetMapping("/patient/profile/{email}")
    public ResponseEntity<PatientProfileDTO> getPatientProfile(@PathVariable String email) {
        PatientProfileDTO profile = patientProfileService.getPatientProfile(email);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/patient/profile/{email}")
    public ResponseEntity<Void> updatePatientProfile(
            @PathVariable String email,
            @RequestBody PatientProfileDTO profileDTO) {
        patientProfileService.updatePatientProfile(email, profileDTO);
        return ResponseEntity.ok().build();
    }
}
