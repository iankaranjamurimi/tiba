package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.VitalsDTO;
import com.tiba.tiba.Services.VitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
public class VitalsController {

    private final VitalsService vitalsService;

    public VitalsController(VitalsService vitalsService) {
        this.vitalsService = vitalsService;
    }

    @PostMapping("/createVitals")
    public ResponseEntity<VitalsDTO> createVitals(@RequestBody VitalsDTO vitalsDTO) {
        VitalsDTO createdVitals = vitalsService.createVitals(vitalsDTO);
        return new ResponseEntity<>(createdVitals, HttpStatus.CREATED);
    }

    @GetMapping("/getVitalsByUserId/{userId}")
    public ResponseEntity<List<VitalsDTO>> getVitalsByUserId(@PathVariable Long userId) {
        List<VitalsDTO> vitalsList = vitalsService.getVitalsByUserId(userId);
        return ResponseEntity.ok(vitalsList);
    }


}