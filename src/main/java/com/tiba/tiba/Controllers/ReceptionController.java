package com.tiba.tiba.Controllers;

import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ReceptionController {

    private final PatientService patientService;


    @GetMapping("/reception/exists/{idNumber}")
    public ResponseEntity<Map<String, Object>> checkPatientAndGetProfile(@PathVariable String idNumber) {
        Optional<Patient> patient = patientService.getPatientByIdNumber(idNumber);
        Map<String, Object> response = new HashMap<>();

        if (patient.isPresent()) {
            response.put("exists", true);
            response.put("patient", Map.of(
                    "idNumber", patient.get().getIdNumber(),
                    "firstName", patient.get().getUser ().getFirstName(),
                    "lastName", patient.get().getUser ().getLastName(),
                    "dateOfBirth", patient.get().getDateOfBirth(),
                    "gender", patient.get().getGender(),
                    "contactNumber", patient.get().getContactNumber()
            ));


            response.put("message", "Patient found. Profile data included.");
            return ResponseEntity.ok(response);
        } else {
            response.put("exists", false);
            response.put("message", "Patient does not exist.");
            return ResponseEntity.status(404).body(response);
        }


    }
}
