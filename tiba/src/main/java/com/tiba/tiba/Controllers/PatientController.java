package com.tiba.tiba.Controllers;

import com.tiba.tiba.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<com.tiba.tiba.Entities.Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.tiba.tiba.Entities.Patient> getPatientById(@PathVariable Integer id) {
        com.tiba.tiba.Entities.Patient patientDTO = patientService.getPatientById(id);
        return patientDTO != null ? ResponseEntity.ok(patientDTO)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public com.tiba.tiba.Entities.Patient createPatient(@RequestBody com.tiba.tiba.Entities.Patient patientDTO) {
        return patientService.savePatient(patientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.tiba.tiba.Entities.Patient> updatePatient(@PathVariable Integer id, @RequestBody com.tiba.tiba.Entities.Patient patientDTO) {
        if (patientService.getPatientById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        patientDTO.setId(id);
        return ResponseEntity.ok(patientService.savePatient(patientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        if (patientService.getPatientById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}