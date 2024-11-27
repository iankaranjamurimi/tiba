package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.AllergiesDTO;
import com.tiba.tiba.Entities.Allergies;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.AllergiesRepository;
import com.tiba.tiba.Repositories.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public AllergiesDTO updateAllergies(Long userId, AllergiesDTO allergiesDTO) {
        // find patient
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Create new or update existing allergies
        Allergies allergies;

        // if UserId is valid find existing allergies
        if (allergiesDTO.getUserId() != null && allergiesDTO.getUserId() > 0) {
            try {
                allergies = allergiesRepository.findById(allergiesDTO.getUserId())
                        .orElseGet(() -> {
                            log.info("Creating new allergy record as ID {} was not found", allergiesDTO.getUserId());
                            return new Allergies();
                        });
            } catch (Exception e) {
                log.error("Error finding allergy with ID: {}", allergiesDTO.getUserId(), e);
                allergies = new Allergies();
            }
        } else {
            log.info("Creating new allergy record as no ID was provided");
            allergies = new Allergies();
        }

        // Update the allergies entity with DTO values
        updateAllergiesFromDTO(allergies, allergiesDTO, user);

        // Save and return
        try {
            Allergies updatedAllergies = allergiesRepository.save(allergies);
            return convertToDTO(updatedAllergies);
        } catch (Exception e) {
            log.error("Error saving allergy record", e);
            throw new RuntimeException("Failed to save allergy record: " + e.getMessage());
        }
    }

    private void updateAllergiesFromDTO(Allergies allergies, AllergiesDTO dto, User user) {
        allergies.setAllergen(dto.getAllergen());
        allergies.setReaction_type(dto.getReaction_type());
        allergies.setTreatmentMedication(dto.getTreatmentMedication());
        allergies.setSeverity(dto.getSeverity());
        allergies.setDate(dto.getDate());
        allergies.setNotes(dto.getNotes());
        allergies.setUser(user);
    }

    private AllergiesDTO convertToDTO(Allergies allergies) {
        AllergiesDTO dto = new AllergiesDTO();
        dto.setUserId(allergies.getUser().getId());
        dto.setAllergen(allergies.getAllergen());
        dto.setReaction_type(allergies.getReaction_type());
        dto.setTreatmentMedication(allergies.getTreatmentMedication());
        dto.setSeverity(allergies.getSeverity());
        dto.setDate(allergies.getDate());
        dto.setNotes(allergies.getNotes());
        return dto;
    }
}