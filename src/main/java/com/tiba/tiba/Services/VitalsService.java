package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.VitalsDTO;
import com.tiba.tiba.Entities.Vitals;
import com.tiba.tiba.Repositories.UserRepository;
import com.tiba.tiba.Repositories.VitalsRepository;
import com.tiba.tiba.Repositories.MedicalRecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VitalsService {

    private final VitalsRepository vitalsRepository;
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final UserRepository userRepository;

    public VitalsDTO createVitals(VitalsDTO vitalsDTO) {
        Vitals vitals = convertToEntity(vitalsDTO);
        Vitals savedVitals = vitalsRepository.save(vitals);
        return convertToDTO(savedVitals);
    }

    public List<VitalsDTO> getVitalsByUserId(Long userId) {
        Optional<Vitals> vitalsList = vitalsRepository.findByUserId(userId);
        return vitalsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private Vitals convertToEntity(VitalsDTO vitalsDTO) {
        Vitals vitals = new Vitals();
        vitals.setTemperature(vitalsDTO.getTemperature());
        vitals.setBpSystolic(vitalsDTO.getBpSystolic());
        vitals.setBpDiastolic(vitalsDTO.getBpDiastolic());
        vitals.setHeartRate(vitalsDTO.getHeartRate());
        vitals.setRespiratoryRate(vitalsDTO.getRespiratoryRate());
        vitals.setOxygenSaturation(vitalsDTO.getOxygenSaturation());
        vitals.setWeight(vitalsDTO.getWeight());

        // Setting relationships
        if (vitalsDTO.getMedicalRecordsId() != null) {
            vitals.setMedicalRecords(medicalRecordsRepository.findById(vitalsDTO.getMedicalRecordsId())
                    .orElseThrow(() -> new RuntimeException("Medical Record not found")));
        }

        if (vitalsDTO.getUserId() != null) {
            vitals.setUser(userRepository.findById(vitalsDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found")));
        }

        return vitals;
    }

    private VitalsDTO convertToDTO(Vitals vitals) {
        VitalsDTO vitalsDTO = new VitalsDTO();
        vitalsDTO.setTemperature(vitals.getTemperature());
        vitalsDTO.setBpSystolic(vitals.getBpSystolic());
        vitalsDTO.setBpDiastolic(vitals.getBpDiastolic());
        vitalsDTO.setHeartRate(vitals.getHeartRate());
        vitalsDTO.setRespiratoryRate(vitals.getRespiratoryRate());
        vitalsDTO.setOxygenSaturation(vitals.getOxygenSaturation());
        vitalsDTO.setWeight(vitals.getWeight());
        vitalsDTO.setMedicalRecordsId(vitals.getMedicalRecords().getId());
        vitalsDTO.setUserId(vitals.getUser().getId());

        if (vitals.getMedicalRecords() != null) {
            vitalsDTO.setMedicalRecordsId(vitals.getMedicalRecords().getId());
        }

        if (vitals.getUser() != null) {
            vitalsDTO.setUserId(vitals.getUser().getId());
        }

        return vitalsDTO;
    }

    private void updateVitalsEntity(Vitals vitals, VitalsDTO vitalsDTO) {
        vitals.setTemperature(vitalsDTO.getTemperature());
        vitals.setBpSystolic(vitalsDTO.getBpSystolic());
        vitals.setBpDiastolic(vitalsDTO.getBpDiastolic());
        vitals.setHeartRate(vitalsDTO.getHeartRate());
        vitals.setRespiratoryRate(vitalsDTO.getRespiratoryRate());
        vitals.setOxygenSaturation(vitalsDTO.getOxygenSaturation());
        vitals.setWeight(vitalsDTO.getWeight());
        vitals.setMedicalRecords(vitals.getMedicalRecords());
        vitals.setUser(vitals.getUser());

        if (vitalsDTO.getMedicalRecordsId() != null) {
            vitals.setMedicalRecords(medicalRecordsRepository.findById(vitalsDTO.getMedicalRecordsId())
                    .orElseThrow(() -> new RuntimeException("Medical Record not found")));
        }

        if (vitalsDTO.getUserId() != null) {
            vitals.setUser(userRepository.findById(vitalsDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found")));
        }
    }
}
