package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.AllergiesDTO;
import com.tiba.tiba.Entities.Allergies;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Repositories.AllergiesRepository;
import com.tiba.tiba.Repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllergiesService {
    private final AllergiesRepository allergiesRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public AllergiesDTO updateAllergies(Long id, AllergiesDTO allergiesDTO) {
        Allergies allergies = allergiesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Allergies not found with id: " + id));

        Patient patient = patientRepository.findById(allergiesDTO.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + allergiesDTO.getPatientId()));

        allergies.setAllergen(allergiesDTO.getAllergen());
        allergies.setReaction_type(allergiesDTO.getReaction_type());
        allergies.setSeverity(allergiesDTO.getSeverity());
        allergies.setDate(allergiesDTO.getDate());
        allergies.setNotes(allergiesDTO.getNotes());
        allergies.setPatient(patient);

        Allergies updatedAllergies = allergiesRepository.save(allergies);
        return convertToDTO(updatedAllergies);
    }

    private AllergiesDTO convertToDTO(Allergies allergies) {
        AllergiesDTO dto = new AllergiesDTO();
        dto.setId(allergies.getId());
        dto.setAllergen(allergies.getAllergen());
        dto.setReaction_type(allergies.getReaction_type());
        dto.setSeverity(allergies.getSeverity());
        dto.setDate(allergies.getDate());
        dto.setNotes(allergies.getNotes());
        dto.setPatientId(allergies.getPatient().getId());
        return dto;
    }
}


