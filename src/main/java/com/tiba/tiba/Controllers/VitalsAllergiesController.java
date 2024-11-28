package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.VitalsAllergiesDTO;
import com.tiba.tiba.Services.VitalsAllergiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class VitalsAllergiesController {
    private final VitalsAllergiesService vitalsAllergiesService;

    @GetMapping("/medical/{userId}")
    public ResponseEntity<VitalsAllergiesDTO> getUserMedicalInfo(@PathVariable Long userId) {
        try {
            VitalsAllergiesDTO medicalInfo = vitalsAllergiesService.getUserMedicalInfo(userId);
            return ResponseEntity.ok(medicalInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}