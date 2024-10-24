package com.tiba.tiba.Services;

import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Optional<Patient> getPatientByIdNumber(String idNumber) {
        return patientRepository.findByIdNumber(idNumber);


    }

    public List<com.tiba.tiba.Entities.Patient> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public com.tiba.tiba.Entities.Patient getPatientById(Integer id) {
        return patientRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public com.tiba.tiba.Entities.Patient savePatient(com.tiba.tiba.Entities.Patient patientDTO) {
        com.tiba.tiba.Entities.Patient patient = convertToEntity(patientDTO);
        return convertToDTO(patientRepository.save(patient));
    }

    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
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

    private com.tiba.tiba.Entities.Patient convertToEntity(com.tiba.tiba.Entities.Patient patientDTO) {
        com.tiba.tiba.Entities.Patient patient = new com.tiba.tiba.Entities.Patient();
        patient.setId(patientDTO.getId());
        patient.setContactNumber(patientDTO.getContactNumber());
        patient.setEmergencyContactNumber(patientDTO.getEmergencyContactNumber());
        patient.setAddress(patientDTO.getAddress());
        patient.setGender(patientDTO.getGender());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setUser(new User());
        return patient;
    }



}