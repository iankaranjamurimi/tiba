package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.PatientResponseDTO;
import com.tiba.tiba.Services.PatientLookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/open")
public class PatientController {

    private final PatientLookupService patientLookupService;

    public PatientController(PatientLookupService patientLookupService) {
        this.patientLookupService = patientLookupService;
    }

    @GetMapping("/lookup/{idNumber}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> lookupPatient(@PathVariable String idNumber) {
        Map<String, Object> response = new HashMap<>();

        try {
            PatientResponseDTO patient = patientLookupService.findPatientByIdNumber(idNumber);
            response.put("status", "success");
            response.put("data", patient);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "error");
            response.put("message", "Patient not found with ID: " + idNumber);
            return ResponseEntity.status(404).body(response);
        }
    }
}