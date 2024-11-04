package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.VitalsDTO;
import com.tiba.tiba.Entities.Vitals;
import com.tiba.tiba.Services.VitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open")
public class VitalsController {

    @Autowired
    private VitalsService vitalsService;

    @PostMapping("/vitals")
    public ResponseEntity<Vitals> addVitals(@RequestBody VitalsDTO vitalsDTO) {
        Vitals savedVitals = vitalsService.addVitals(vitalsDTO);
        return ResponseEntity.ok(savedVitals);
        }
    }
