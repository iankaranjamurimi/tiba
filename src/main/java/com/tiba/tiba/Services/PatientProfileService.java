package com.tiba.tiba.Services;

import org.springframework.stereotype.Service;
import com.tiba.tiba.DTO.PatientProfileDTO;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PatientProfileService {

    private final UserSignUpRepository userSignUpRepository;

    public PatientProfileService(UserSignUpRepository userSignUpRepository) {
        this.userSignUpRepository = userSignUpRepository;
    }

    public PatientProfileDTO getPatientProfile(Long userId) {
        User user = userSignUpRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Patient patient = user.getPatient();
        PatientProfileDTO profileDTO = new PatientProfileDTO();

        // Map to DTO
        profileDTO.setFirstName(user.getFirstName());
        profileDTO.setMiddleName(user.getMiddleName());
        profileDTO.setLastName(user.getLastName());
        profileDTO.setEmail(user.getEmail());
        profileDTO.setDateOfBirth(patient.getDateOfBirth());
        profileDTO.setGender(patient.getGender());
        profileDTO.setIdNumber(patient.getIdNumber());
        profileDTO.setContactNumber(patient.getContactNumber());
        profileDTO.setAddress(patient.getAddress());
        profileDTO.setEmergencyContactNumber(patient.getEmergencyContactNumber());

        return profileDTO;
    }

    @Transactional
    public void updatePatientProfile(Long userId, PatientProfileDTO profileDTO) {
        User user = userSignUpRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Patient patient = user.getPatient();

        // Updating both user and patient details
        user.setFirstName(profileDTO.getFirstName());
        user.setMiddleName(profileDTO.getMiddleName());
        user.setLastName(profileDTO.getLastName());

        patient.setDateOfBirth(profileDTO.getDateOfBirth());
        patient.setGender(profileDTO.getGender());
        patient.setIdNumber(profileDTO.getIdNumber());
        patient.setContactNumber(profileDTO.getContactNumber());
        patient.setAddress(profileDTO.getAddress());
        patient.setEmergencyContactNumber(profileDTO.getEmergencyContactNumber());

        userSignUpRepository.save(user);
    }
}
