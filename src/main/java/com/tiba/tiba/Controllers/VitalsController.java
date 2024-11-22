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

    @PostMapping("/vitals")
    public ResponseEntity<VitalsDTO> createVitals(@RequestBody VitalsDTO vitalsDTO) {
        VitalsDTO createdVitals = vitalsService.createVitals(vitalsDTO);
        return new ResponseEntity<>(createdVitals, HttpStatus.CREATED);
    }

    @GetMapping("/vitals/{id}")
    public ResponseEntity<VitalsDTO> getVitalsById(@PathVariable Long id) {
        VitalsDTO vitals = vitalsService.getVitalsById(id);
        return ResponseEntity.ok(vitals);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VitalsDTO>> getVitalsByUserId(@PathVariable Long userId) {
        List<VitalsDTO> vitalsList = vitalsService.getVitalsByUserId(userId);
        return ResponseEntity.ok(vitalsList);
    }

    @PutMapping("/p/{id}")
    public ResponseEntity<VitalsDTO> updateVitals(@PathVariable Long id, @RequestBody VitalsDTO vitalsDTO) {
        VitalsDTO updatedVitals = vitalsService.updateVitals(id, vitalsDTO);
        return ResponseEntity.ok(updatedVitals);
    }

    @DeleteMapping("/d/{id}")
    public ResponseEntity<Void> deleteVitals(@PathVariable Long id) {
        vitalsService.deleteVitals(id);
        return ResponseEntity.noContent().build();
    }
}