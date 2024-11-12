package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.Vitals_AllergiesDTO;
import com.tiba.tiba.Services.VitalsAllergiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open")
public class VitalsAllergiesController {

    private final VitalsAllergiesService vitalsAllergiesService;

    @Autowired
    public VitalsAllergiesController(VitalsAllergiesService vitalsAllergiesService) {
        this.vitalsAllergiesService = vitalsAllergiesService;
    }

    @GetMapping("/medical/{userId}")
    public ResponseEntity<Vitals_AllergiesDTO> getUserMedicalInfo(@PathVariable Long userId) {
        try {
            Vitals_AllergiesDTO medicalInfo = vitalsAllergiesService.getUserMedicalInfo(userId);
            return ResponseEntity.ok(medicalInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}