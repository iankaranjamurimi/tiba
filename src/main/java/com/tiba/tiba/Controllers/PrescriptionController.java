package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.PrescriptionDTO;
import com.tiba.tiba.Services.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/prescriptions/{userId}/{hospitalStaffId}")
    public ResponseEntity<PrescriptionDTO> createPrescription(
            @PathVariable Long userId,
            @PathVariable Long hospitalStaffId,
            @RequestBody PrescriptionDTO prescriptionDTO) {
        prescriptionDTO.setUserId(userId);
        prescriptionDTO.setHospitalStaffId(hospitalStaffId);
        PrescriptionDTO createdPrescription = prescriptionService.createPrescription(prescriptionDTO);
        return new ResponseEntity<>(createdPrescription, HttpStatus.CREATED);
    }

    @GetMapping("/prescriptions-user/{userId}")
    public ResponseEntity<List<PrescriptionDTO>> getUserPrescriptions(@PathVariable Long userId) {
        List<PrescriptionDTO> prescriptions = prescriptionService.findPrescriptionsByUserId(userId);
        return ResponseEntity.ok(prescriptions);
    }

}

