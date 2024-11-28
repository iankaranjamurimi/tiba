package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.PatientResponseDTO;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientLookupService {
    private final PatientRepository patientRepository;

    public PatientResponseDTO findPatientByIdNumber(String idNumber) {
        Patient patient = patientRepository.findByIdNumber(idNumber)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + idNumber));

        return convertToDTO(patient);
    }

    private PatientResponseDTO convertToDTO(Patient patient) {
        return PatientResponseDTO.builder()
                .userId(patient.getUser().getId())
                .firstName(patient.getUser().getFirstName())
                .middleName(patient.getUser().getMiddleName())
                .lastName(patient.getUser().getLastName())
                .email(patient.getUser().getEmail())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .idNumber(patient.getIdNumber())
                .contactNumber(patient.getContactNumber())
                .build();
    }
}