package com.tiba.tiba.Controllers;

import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//http://localhost:8080/tiba/patients/{idNumber}
@RestController
@RequestMapping("/api/open")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping
    public List<com.tiba.tiba.Entities.Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/exists/{idNumber}")
    public ResponseEntity<Map<String, Object>> checkPatientIdExists(@PathVariable String idNumber) {
        boolean exists = patientService.getPatientByIdNumber(idNumber).isPresent();

        Map<String, Object> response = new HashMap<>();
        response.put("exists", exists);
        response.put("message", exists ? "Patient exists." : "Patient does not exist.");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/firstname/check/{idNumber}")
    public ResponseEntity<Map<String, Object>> getPatientFirstNameIfExists(@PathVariable String idNumber) {
        Optional<Patient> patient = patientService.getPatientByIdNumber(idNumber);

        Map<String, Object> response = new HashMap<>();
        if (patient.isPresent()) {
            response.put("exists", true);
            response.put("firstName", patient.get().getUser().getFirstName());
            response.put("message", "Patient exists.");
            return ResponseEntity.ok(response);
        } else {
            response.put("exists", false);
            response.put("message", "Patient does not exist.");
            return ResponseEntity.status(404).body(response);
        }
    }



    @PostMapping
    public com.tiba.tiba.Entities.Patient createPatient(@RequestBody com.tiba.tiba.Entities.Patient patientDTO) {
        return patientService.savePatient(patientDTO);
    }

    @PutMapping("/id")
    public ResponseEntity<com.tiba.tiba.Entities.Patient> updatePatient(@PathVariable Integer id, @RequestBody com.tiba.tiba.Entities.Patient patientDTO) {
        if (patientService.getPatientById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        patientDTO.setId(Long.valueOf(id));
        return ResponseEntity.ok(patientService.savePatient(patientDTO));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        if (patientService.getPatientById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/submit")
//    public ResponseEntity<Void> submitPatientProfile(@RequestBody Patient patient) {
//        patientService.submitPatientProfile(patient);
//        return ResponseEntity.ok().build();
//    }

    //https://tiba.onrender.com/swagger-ui/index.html#/patient-controller/getPatientById
}