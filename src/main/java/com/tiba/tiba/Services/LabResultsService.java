package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.LabResultsDTO;
import com.tiba.tiba.Entities.LabResults;
import com.tiba.tiba.Entities.MedicalRecords;
import com.tiba.tiba.Repositories.LabResultsRepository;
import com.tiba.tiba.Repositories.MedicalRecordsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LabResultsService {

    private final LabResultsRepository labResultsRepository;

    private final MedicalRecordsRepository medicalRecordsRepository;

    @Transactional
    public LabResultsDTO createLabResults(LabResultsDTO labResultsDTO) {
        MedicalRecords medicalRecords = medicalRecordsRepository.findById(labResultsDTO.getMedicalRecordsId())
                .orElseThrow(() -> new EntityNotFoundException("Medical Records not found with id: " +
                        labResultsDTO.getMedicalRecordsId()));

        LabResults labResults = new LabResults();
        updateLabResultsFromDTO(labResults, labResultsDTO, medicalRecords);

        LabResults savedLabResults = labResultsRepository.save(labResults);
        return convertToDTO(savedLabResults);
    }

    private void updateLabResultsFromDTO(LabResults labResults, LabResultsDTO dto, MedicalRecords medicalRecords) {
        labResults.setTestName(dto.getTestName());
        labResults.setTestResult(dto.getTestResult());
        labResults.setNotes(dto.getNotes());
        labResults.setPerformedAt(dto.getPerformedAt());
        labResults.setHospitalName(dto.getHospitalName());
        labResults.setMedicalRecords(medicalRecords);
    }

    private LabResultsDTO convertToDTO(LabResults labResults) {
        LabResultsDTO dto = new LabResultsDTO();
        dto.setTestName(labResults.getTestName());
        dto.setTestResult(labResults.getTestResult());
        dto.setNotes(labResults.getNotes());
        dto.setPerformedAt(labResults.getPerformedAt());
        dto.setHospitalName(labResults.getHospitalName());
        dto.setMedicalRecordsId(labResults.getMedicalRecords().getId());
        return dto;
    }
    public List<LabResultsDTO> getLabResultsByUserId(Long userId) {
        // First find medical records for the user
        List<MedicalRecords> medicalRecords = medicalRecordsRepository.findByUserId(userId);
        if (medicalRecords.isEmpty()) {
            throw new EntityNotFoundException("No medical records found for user with id: " + userId);
        }

        // Then find all lab results for these medical records
        List<LabResults> labResults = new ArrayList<>();
        for (MedicalRecords record : medicalRecords) {
            labResults.addAll(labResultsRepository.findByMedicalRecordsId(record.getId()));
        }

        // Convert to DTOs
        return labResults.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
