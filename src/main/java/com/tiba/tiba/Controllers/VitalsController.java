package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.VitalsDTO;
import com.tiba.tiba.Entities.Vitals;
import com.tiba.tiba.Services.VitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open")
public class VitalsController {

    @Autowired
    private VitalsService vitalsService;

    @PutMapping("/vitals")
    public ResponseEntity<Vitals> addVitals(@RequestBody VitalsDTO vitalsDTO) {
        Vitals savedVitals = vitalsService.addVitals(vitalsDTO);
        return ResponseEntity.ok(savedVitals);
    }

    @GetMapping("vitals/{patientId}")
    public List<Vitals> getVitalsByPatientId(@PathVariable Long patientId) {
        return vitalsService.getVitalsByPatientId(patientId);
    }
//    @GetMapping("/{medicalRecordId}")
//        public ResponseEntity<VitalsDTO> getVitalsByMedicalRecordId(@PathVariable Long medicalRecordId) {
//            return vitalsService.getVitalsByMedicalRecordId(medicalRecordId)
//                    .map(ResponseEntity::ok)
//                    .orElse(ResponseEntity.notFound().build());
//    }
//
//        @PostMapping
//        public ResponseEntity<VitalsDTO> createVitals(@RequestBody VitalsDTO vitalsDTO) {
//            return ResponseEntity.ok(vitalsService.saveVitals(vitalsDTO));
//        }
//
//        @PutMapping("/{medicalRecordId}")
//        public ResponseEntity<VitalsDTO> updateVitals(
//                @PathVariable Long medicalRecordId,
//                @RequestBody VitalsDTO vitalsDTO) {
//            return ResponseEntity.ok(vitalsService.updateVitals(medicalRecordId, vitalsDTO));
//        }
//
//        @DeleteMapping("/{medicalRecordId}")
//        public ResponseEntity<Void> deleteVitals(@PathVariable Long medicalRecordId) {
//            vitalsService.deleteVitals(medicalRecordId);
//            return ResponseEntity.noContent().build();
//        }
//    }
    }
