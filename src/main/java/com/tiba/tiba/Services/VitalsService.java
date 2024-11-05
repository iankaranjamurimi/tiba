package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.VitalsDTO;
import com.tiba.tiba.Entities.MedicalRecords;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Entities.Vitals;
import com.tiba.tiba.Repositories.MedicalRecordsRepository;
import com.tiba.tiba.Repositories.VitalsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VitalsService {

    @Autowired
    private VitalsRepository vitalsRepository;

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Transactional
    public Vitals addVitals(VitalsDTO vitalsDTO) {
        // Finding the  associated medical record

        MedicalRecords medicalRecords = medicalRecordsRepository.findById(vitalsDTO.getMedicalRecordsId())
                .orElseThrow(() -> new RuntimeException("Medical Record not found"));


        Vitals vitals = new Vitals();
        vitals.setTemperature(vitalsDTO.getTemperature());
        vitals.setBpSystolic(vitalsDTO.getBpSystolic());
        vitals.setBpDiastolic(vitalsDTO.getBpDiastolic());
        vitals.setHeartRate(vitalsDTO.getHeartRate());
        vitals.setRespiratoryRate(vitalsDTO.getRespiratoryRate());
        vitals.setOxygenSaturation(vitalsDTO.getOxygenSaturation());
        vitals.setWeight(vitalsDTO.getWeight());
        vitals.setMedicalRecords(medicalRecords);
        vitals.setPatient(new Patient());

        // Save and return the vitals form
        return vitalsRepository.save(vitals);
    }

    public List<Vitals> getVitalsByPatientId(Long patientId) {
        // Fetch medical records associated with the patient
        MedicalRecords medicalRecords = medicalRecordsRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Medical Record not found for patient ID: " + patientId));

        // Retrieve vitals associated with the medical records
        return vitalsRepository.findByMedicalRecordsId(medicalRecords.getId());
    }

    public List<VitalsDTO> getAllVitals() {
            return null;
    }
}
