package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.MedicalRecordsUpdateDTO;
import com.tiba.tiba.Entities.MedicalRecords;
import com.tiba.tiba.Services.MedicalRecordsUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
public class MedicalRecordsUpdateController {
    @Autowired
    private MedicalRecordsUpdateService medicalRecordsUpdateService;

    @PostMapping("/medicalRecords/{id}")
    public ResponseEntity<MedicalRecords> MedicalRecords(
            @PathVariable("id") Long id,
            @RequestBody MedicalRecordsUpdateDTO updateDTO
    ) {
        MedicalRecords MedicalRecords = medicalRecordsUpdateService.MedicalRecords(id, updateDTO);
        return ResponseEntity.ok(MedicalRecords);
    }
}

