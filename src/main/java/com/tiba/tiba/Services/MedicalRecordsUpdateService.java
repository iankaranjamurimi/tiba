package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.MedicalRecordsUpdateDTO;
import com.tiba.tiba.Entities.MedicalRecords;
import com.tiba.tiba.Repositories.MedicalRecordsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordsUpdateService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Transactional
    public MedicalRecords updateMedicalRecords(Long id, MedicalRecordsUpdateDTO updateDTO) {
        MedicalRecords medicalRecords = medicalRecordsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical Records not found"));
        medicalRecords.setNotes(updateDTO.getNotes());
        medicalRecords.setDiagnosis(updateDTO.getDiagnosis());
        medicalRecords.setTreatment(updateDTO.getTreatment());
        medicalRecords.setMedication(updateDTO.getMedication());
        medicalRecords.setSubmittedAt(updateDTO.getSubmittedAt());
        medicalRecords.setSubmittedBy(updateDTO.getSubmittedBy());
        medicalRecords.setFollowUpRequired(updateDTO.getFollowUpRequired());
        medicalRecords.setFollowUpDate(updateDTO.getFollowUpDate());

        return medicalRecordsRepository.save(medicalRecords);
        }

    public MedicalRecords MedicalRecords(Long id, MedicalRecordsUpdateDTO updateDTO) {
        return null;
    }
}
