package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.MedicalRecordsUpdateDTO;
import com.tiba.tiba.Services.MedicalRecordsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MedicalRecordsController {
    private final MedicalRecordsService medicalRecordsService;

    @PostMapping("/create/medicalRecords")
    public ResponseEntity<MedicalRecordsUpdateDTO> createMedicalRecord(
            @Valid @RequestBody MedicalRecordsUpdateDTO medicalRecordsDTO) {
        return new ResponseEntity<>(
                medicalRecordsService.createMedicalRecord(medicalRecordsDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/medicalRecords/{id}")
    public ResponseEntity<MedicalRecordsUpdateDTO> updateMedicalRecord(
            @PathVariable Long id,
            @Valid @RequestBody MedicalRecordsUpdateDTO medicalRecordsDTO) {
        return ResponseEntity.ok(medicalRecordsService.updateMedicalRecord(id, medicalRecordsDTO));
    }

    @GetMapping("/medicalRecords/{userId}")
    public ResponseEntity<List<MedicalRecordsUpdateDTO>> getPatientMedicalRecords(
            @PathVariable Long userId) {
        return ResponseEntity.ok(medicalRecordsService.getPatientMedicalRecords(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordsUpdateDTO> getMedicalRecord(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordsService.getMedicalRecord(id));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
