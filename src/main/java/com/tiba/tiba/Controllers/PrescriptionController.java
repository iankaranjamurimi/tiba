package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.PrescriptionDTO;
import com.tiba.tiba.Services.PrescriptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public PrescriptionDTO createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        return prescriptionService.createPrescription(prescriptionDTO);
    }

    @GetMapping("/{id}")
    public PrescriptionDTO getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    // Add more CRUD endpoints as needed
}
