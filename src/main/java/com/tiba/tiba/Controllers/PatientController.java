package com.tiba.tiba.Controllers;

import com.tiba.tiba.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/tiba/patients/{idNumber}
@RestController
@RequestMapping("tiba/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping
    public List<com.tiba.tiba.Entities.Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

//    @GetMapping("/idNumber")
//    public ResponseEntity<Boolean> getPatientByIdNumber(@PathVariable Integer idNumber) {
//        com.tiba.tiba.Entities.Patient SignUPDTO = patientService.getPatientById(idNumber);
//        return SignUPDTO != null ? ResponseEntity.ok(SignUPDTO)
//                : ResponseEntity.notFound().build();
//    }
@GetMapping("/exists/{idNumber}")
public ResponseEntity<Boolean> checkPatientIdExists(@PathVariable String idNumber) {
    boolean exists = patientService.getPatientByIdNumber(idNumber).isPresent();
    return ResponseEntity.ok(exists);
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
        patientDTO.setId(id);
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

    //https://tiba.onrender.com/swagger-ui/index.html#/patient-controller/getPatientById
}