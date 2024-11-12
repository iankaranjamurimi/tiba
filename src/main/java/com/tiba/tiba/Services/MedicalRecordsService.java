
package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.MedicalRecordsUpdateDTO;
import com.tiba.tiba.Entities.MedicalRecords;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.MedicalRecordsRepository;
import com.tiba.tiba.Repositories.PatientRepository;
import com.tiba.tiba.Repositories.HospitalStaffRepository;
import com.tiba.tiba.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordsService {
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final PatientRepository patientRepository;
    private final HospitalStaffRepository hospitalStaffRepository;
    private final UserRepository userRepository;

    @Transactional
    public MedicalRecordsUpdateDTO createMedicalRecord(MedicalRecordsUpdateDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + dto.getUserId()));

        MedicalRecords medicalRecords = new MedicalRecords();
        updateMedicalRecordFromDTO(medicalRecords, dto, user); /*staff*/

        MedicalRecords savedRecord = medicalRecordsRepository.save(medicalRecords);
        return convertToDTO(savedRecord);
    }

    @Transactional
    public MedicalRecordsUpdateDTO updateMedicalRecord(Long id, MedicalRecordsUpdateDTO dto) {
        MedicalRecords existingRecord = medicalRecordsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical record not found with id: " + id));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + dto.getUserId()));

//        HospitalStaff staff = hospitalStaffRepository.findById(dto.getHospitalStaffId())
//                .orElseThrow(() -> new EntityNotFoundException("Hospital staff not found with id: " + dto.getHospitalStaffId()));

        updateMedicalRecordFromDTO(existingRecord, dto, user); /*staff*/
        MedicalRecords updatedRecord = medicalRecordsRepository.save(existingRecord);
        return convertToDTO(updatedRecord);
    }

    @Transactional(readOnly = true)
    public List<MedicalRecordsUpdateDTO> getPatientMedicalRecords(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + userId));

        return medicalRecordsRepository.findByuserId (user.getId()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MedicalRecordsUpdateDTO getMedicalRecord(Long id) {
        MedicalRecords record = medicalRecordsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical record not found with id: " + id));
        return convertToDTO(record);
    }

    private void updateMedicalRecordFromDTO(MedicalRecords record, MedicalRecordsUpdateDTO dto,
                                            User user) {
        record.setNotes(dto.getNotes());
        record.setDiagnosis(dto.getDiagnosis());
        record.setTreatment(dto.getTreatment());
        record.setSubmittedAt(dto.getSubmittedAt());
        record.setSubmittedBy(dto.getSubmittedBy());
//        record.setUser(user);

    }

    private MedicalRecordsUpdateDTO convertToDTO(MedicalRecords record) {
        MedicalRecordsUpdateDTO dto = new MedicalRecordsUpdateDTO();
        dto.setNotes(record.getNotes());
        dto.setDiagnosis(record.getDiagnosis());
        dto.setTreatment(record.getTreatment());
        dto.setSubmittedAt(record.getSubmittedAt());
        dto.setSubmittedBy(record.getSubmittedBy());
//        dto.setUserId(record.getUser().getId());
        return dto;
    }
}