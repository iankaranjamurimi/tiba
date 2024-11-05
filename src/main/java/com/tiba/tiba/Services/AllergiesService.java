package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.AllergiesDTO;
import com.tiba.tiba.Entities.Allergies;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Repositories.AllergiesRepository;
import com.tiba.tiba.Repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AllergiesService {
    private final AllergiesRepository allergiesRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public AllergiesDTO updateAllergies(Long patientId, AllergiesDTO allergiesDTO) {
        // find the patient
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        // Create new allergies if it doesn't exist, or update existing ones
        Allergies allergies;

        // Only try to find existing allergies if we have a valid ID
        if (allergiesDTO.getPatientId() != null && allergiesDTO.getPatientId() > 0) {
            try {
                allergies = allergiesRepository.findById(allergiesDTO.getPatientId())
                        .orElseGet(() -> {
                            log.info("Creating new allergy record as ID {} was not found", allergiesDTO.getPatientId());
                            return new Allergies();
                        });
            } catch (Exception e) {
                log.error("Error finding allergy with ID: {}", allergiesDTO.getPatientId(), e);
                allergies = new Allergies();
            }
        } else {
            log.info("Creating new allergy record as no ID was provided");
            allergies = new Allergies();
        }

        // Update the allergies entity with DTO values
        updateAllergiesFromDTO(allergies, allergiesDTO, patient);

        // Save and return
        try {
            Allergies updatedAllergies = allergiesRepository.save(allergies);
            return convertToDTO(updatedAllergies);
        } catch (Exception e) {
            log.error("Error saving allergy record", e);
            throw new RuntimeException("Failed to save allergy record: " + e.getMessage());
        }
    }

    private void updateAllergiesFromDTO(Allergies allergies, AllergiesDTO dto, Patient patient) {
        allergies.setAllergen(dto.getAllergen());
        allergies.setReaction_type(dto.getReaction_type());
        allergies.setSeverity(dto.getSeverity());
        allergies.setDate(dto.getDate());
        allergies.setNotes(dto.getNotes());
        allergies.setPatient(patient);
    }

    private AllergiesDTO convertToDTO(Allergies allergies) {
        AllergiesDTO dto = new AllergiesDTO();
        dto.setPatientId(allergies.getPatient().getId());
        dto.setAllergen(allergies.getAllergen());
        dto.setReaction_type(allergies.getReaction_type());
        dto.setSeverity(allergies.getSeverity());
        dto.setDate(allergies.getDate());
        dto.setNotes(allergies.getNotes());
        return dto;
    }
}








//package com.tiba.tiba.Services;
//
//import com.tiba.tiba.DTO.AllergiesDTO;
//import com.tiba.tiba.Entities.Allergies;
//import com.tiba.tiba.Entities.Patient;
//import com.tiba.tiba.Repositories.AllergiesRepository;
//import com.tiba.tiba.Repositories.PatientRepository;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AllergiesService {
//    private final AllergiesRepository allergiesRepository;
//    private final PatientRepository patientRepository;
//
//    @Transactional
//    public AllergiesDTO updateAllergies(Long patientId, AllergiesDTO allergiesDTO) {
//        // find the patient
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
//
//        // Create new allergies if it doesn't exist, or update existing ones
//        Allergies allergies;
//        if (allergiesDTO.getPatientId() != null) {
//            allergies = allergiesRepository.findById(allergiesDTO.getPatientId())
//                    .orElseThrow(() -> new EntityNotFoundException("Allergies not found with id: " + allergiesDTO.getPatientId()));
//        } else {
//            allergies = new Allergies();
//        }
//
//        allergies.setAllergen(allergiesDTO.getAllergen());
//        allergies.setReaction_type(allergiesDTO.getReaction_type());
//        allergies.setSeverity(allergiesDTO.getSeverity());
//        allergies.setDate(allergiesDTO.getDate());
//        allergies.setNotes(allergiesDTO.getNotes());
//        allergies.setPatient(patient);
//
//        Allergies updatedAllergies = allergiesRepository.save(allergies);
//        return convertToDTO(updatedAllergies);
//    }
//
//    private AllergiesDTO convertToDTO(Allergies allergies) {
//        AllergiesDTO dto = new AllergiesDTO();
////        dto.setId(allergies.getId());
//        dto.setAllergen(allergies.getAllergen());
//        dto.setReaction_type(allergies.getReaction_type());
//        dto.setSeverity(allergies.getSeverity());
//        dto.setDate(allergies.getDate());
//        dto.setNotes(allergies.getNotes());
//        dto.setPatientId(allergies.getPatient().getId());
//        return dto;
//    }
//}