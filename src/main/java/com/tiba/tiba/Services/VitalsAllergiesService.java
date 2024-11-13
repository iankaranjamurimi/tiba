package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.VitalsAllergiesDTO;
import com.tiba.tiba.Entities.Allergies;
import com.tiba.tiba.Entities.Vitals;
import com.tiba.tiba.Repositories.AllergiesRepository;
import com.tiba.tiba.Repositories.VitalsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VitalsAllergiesService {
private final VitalsRepository vitalsRepository;
private final AllergiesRepository allergiesRepository;

@Autowired
public VitalsAllergiesService(VitalsRepository vitalsRepository, AllergiesRepository allergiesRepository) {
    this.vitalsRepository = vitalsRepository;
    this.allergiesRepository = allergiesRepository;
}
public VitalsAllergiesDTO getUserMedicalInfo(Long userId) {
    VitalsAllergiesDTO medicalDTO = new VitalsAllergiesDTO();

            // Get vitals
    Optional<Vitals> vitals = vitalsRepository.findByUserId(userId);
    vitals.ifPresent(v -> {
        medicalDTO.setHeight(Double.valueOf(v.getHeight()));
        medicalDTO.setWeight(Double.valueOf(v.getWeight()));
        medicalDTO.setBpSystolic(String.valueOf(v.getBpSystolic()));
        medicalDTO.setBpDiastolic(String.valueOf(v.getBpDiastolic()));
        medicalDTO.setBloodGroup(v.getBloodGroup());
    });
    // Get allergies
    List<Allergies> allergiesList = allergiesRepository.findByUserId(userId);
    List<String> allergyName = allergiesList.stream()
            .map(Allergies::getAllergen)
            .collect(Collectors.toList());
    medicalDTO.setAllergies(allergyName);
    List<String> allergyMedication = allergiesList.stream()
            .map(Allergies::getTreatmentMedication)
            .collect(Collectors.toList());
    medicalDTO.setAllergies(allergyMedication);

    return medicalDTO;
}
}

