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

@Service
@RequiredArgsConstructor
public class LabResultsService {

    @Autowired
    private LabResultsRepository labResultsRepository;

    @Autowired
    private  MedicalRecordsRepository medicalRecordsRepository;

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
        labResults.setTestCode(dto.getTestCode());
        labResults.setResultValue(dto.getResultValue());
        labResults.setUnit(dto.getUnit());
        labResults.setReferenceRange(dto.getReferenceRange());
        labResults.setStatus(dto.getStatus());
        labResults.setPerformedAt(dto.getPerformedAt());
        labResults.setReportedAt(dto.getReportedAt());
        labResults.setNotes(dto.getNotes());
        labResults.setMedicalRecords(medicalRecords);
    }

    private LabResultsDTO convertToDTO(LabResults labResults) {
        LabResultsDTO dto = new LabResultsDTO();
        dto.setTestName(labResults.getTestName());
        dto.setTestCode(labResults.getTestCode());
        dto.setResultValue(labResults.getResultValue());
        dto.setUnit(labResults.getUnit());
        dto.setReferenceRange(labResults.getReferenceRange());
        dto.setStatus(labResults.getStatus());
        dto.setPerformedAt(labResults.getPerformedAt());
        dto.setReportedAt(labResults.getReportedAt());
        dto.setNotes(labResults.getNotes());
        dto.setMedicalRecordsId(labResults.getMedicalRecords().getId());
        return dto;
    }
}
