package com.tiba.tiba.Services;

import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Optional<Patient> getPatientByIdNumber(String idNumber) {
        return patientRepository.findByIdNumber(idNumber);

    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private com.tiba.tiba.Entities.Patient convertToDTO(com.tiba.tiba.Entities.Patient patient) {
        com.tiba.tiba.Entities.Patient dto = new com.tiba.tiba.Entities.Patient();
        dto.setId(patient.getId());
        dto.setContactNumber(patient.getContactNumber());
        dto.setEmergencyContactNumber(patient.getEmergencyContactNumber());
        dto.setAddress(patient.getAddress());
        dto.setGender(patient.getGender());
        dto.setDateOfBirth(patient.getDateOfBirth());
        return dto;
    }

}