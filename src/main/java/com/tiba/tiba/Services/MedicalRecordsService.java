package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.MedicalRecordsUpdateDTO;
import com.tiba.tiba.Entities.MedicalRecords;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Entities.Hospital;
import com.tiba.tiba.Repositories.MedicalRecordsRepository;
import com.tiba.tiba.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordsService {
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final UserRepository userRepository;
    private final HospitalService hospitalService;

    @Transactional
    public MedicalRecordsUpdateDTO createMedicalRecord(MedicalRecordsUpdateDTO dto) {
        validateMedicalRecordDTO(dto);

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + dto.getUserId()));

        // Using HospitalService
        Hospital hospital = null;
        if (dto.getHospitalName() != null && !dto.getHospitalName().trim().isEmpty()) {
            hospital = hospitalService.getOrCreateHospital(dto.getHospitalName());
        }

        MedicalRecords medicalRecords = new MedicalRecords();
        updateMedicalRecordFromDTO(medicalRecords, dto, user, hospital);

        MedicalRecords savedRecord = medicalRecordsRepository.save(medicalRecords);
        return convertToDTO(savedRecord);
    }

    @Transactional
    public MedicalRecordsUpdateDTO updateMedicalRecord(Long id, MedicalRecordsUpdateDTO dto) {
        validateMedicalRecordDTO(dto);

        MedicalRecords existingRecord = medicalRecordsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical record not found with id: " + id));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + dto.getUserId()));

        if (!Objects.equals(existingRecord.getUser().getId(), user.getId())) {
            throw new SecurityException("Not authorized to update this medical record");
        }

        // Using HospitalService
        Hospital hospital = null;
        if (dto.getHospitalName() != null && !dto.getHospitalName().trim().isEmpty()) {
            hospital = hospitalService.getOrCreateHospital(dto.getHospitalName());
        }

        updateMedicalRecordFromDTO(existingRecord, dto, user, hospital);
        MedicalRecords updatedRecord = medicalRecordsRepository.save(existingRecord);
        return convertToDTO(updatedRecord);
    }

    @Transactional(readOnly = true)
    public List<MedicalRecordsUpdateDTO> getPatientMedicalRecords(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + userId));

        return medicalRecordsRepository.findByUserId(user.getId()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MedicalRecordsUpdateDTO getMedicalRecord(Long id) {
        MedicalRecords record = medicalRecordsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical record not found with id: " + id));
        return convertToDTO(record);
    }

    private void validateMedicalRecordDTO(MedicalRecordsUpdateDTO dto) {
        if (dto.getUserId() == null) {
            throw new ValidationException("User ID is required");
        }
        if (dto.getDiagnosis() == null || dto.getDiagnosis().trim().isEmpty()) {
            throw new ValidationException("Diagnosis is required");
        }
        if (dto.getSubmittedAt() == null) {
            throw new ValidationException("Submission date is required");
        }
        if (dto.getSubmittedBy() == null || dto.getSubmittedBy().trim().isEmpty()) {
            throw new ValidationException("Submitter information is required");
        }
    }

    private void updateMedicalRecordFromDTO(MedicalRecords record, MedicalRecordsUpdateDTO dto,
                                            User user, Hospital hospital) {
        record.setUser(user);
        record.setHospital(hospital);
        record.setNotes(dto.getNotes());
        record.setDiagnosis(dto.getDiagnosis());
        record.setTreatment(dto.getTreatment());
        record.setSubmittedAt(dto.getSubmittedAt());
        record.setSubmittedBy(dto.getSubmittedBy());
    }

    private MedicalRecordsUpdateDTO convertToDTO(MedicalRecords record) {
        MedicalRecordsUpdateDTO dto = new MedicalRecordsUpdateDTO();
        dto.setNotes(record.getNotes());
        dto.setDiagnosis(record.getDiagnosis());
        dto.setTreatment(record.getTreatment());
        dto.setSubmittedAt(record.getSubmittedAt());
        dto.setSubmittedBy(record.getSubmittedBy());
        dto.setUserId(record.getUser().getId());

        if (record.getHospital() != null) {
            dto.setHospitalName(record.getHospital().getHospitalName());
        }

        return dto;
    }
    @Transactional(readOnly = true)
    public List<MedicalRecordsUpdateDTO> getAllMedicalRecords() {
        return medicalRecordsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}