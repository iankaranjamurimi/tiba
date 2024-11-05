
package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.MedicalRecordsUpdateDTO;
import com.tiba.tiba.Entities.MedicalRecords;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Entities.HospitalStaff;
import com.tiba.tiba.Repositories.MedicalRecordsRepository;
import com.tiba.tiba.Repositories.PatientRepository;
import com.tiba.tiba.Repositories.HospitalStaffRepository;
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

    @Transactional
    public MedicalRecordsUpdateDTO createMedicalRecord(MedicalRecordsUpdateDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + dto.getPatientId()));

        HospitalStaff staff = hospitalStaffRepository.findById(dto.getHospitalStaffId())
                .orElseThrow(() -> new EntityNotFoundException("Hospital staff not found with id: " + dto.getHospitalStaffId()));

        MedicalRecords medicalRecords = new MedicalRecords();
        updateMedicalRecordFromDTO(medicalRecords, dto, patient, staff);

        MedicalRecords savedRecord = medicalRecordsRepository.save(medicalRecords);
        return convertToDTO(savedRecord);
    }

    @Transactional
    public MedicalRecordsUpdateDTO updateMedicalRecord(Long id, MedicalRecordsUpdateDTO dto) {
        MedicalRecords existingRecord = medicalRecordsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical record not found with id: " + id));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + dto.getPatientId()));

        HospitalStaff staff = hospitalStaffRepository.findById(dto.getHospitalStaffId())
                .orElseThrow(() -> new EntityNotFoundException("Hospital staff not found with id: " + dto.getHospitalStaffId()));

        updateMedicalRecordFromDTO(existingRecord, dto, patient, staff);
        MedicalRecords updatedRecord = medicalRecordsRepository.save(existingRecord);
        return convertToDTO(updatedRecord);
    }

    @Transactional(readOnly = true)
    public List<MedicalRecordsUpdateDTO> getPatientMedicalRecords(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        return medicalRecordsRepository.findByPatient (patient).stream()
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
                                            Patient patient, HospitalStaff staff) {
        record.setNotes(dto.getNotes());
        record.setDiagnosis(dto.getDiagnosis());
        record.setTreatment(dto.getTreatment());
//        record.setMedication(dto.getMedication());
        record.setSubmittedAt(dto.getSubmittedAt());
        record.setSubmittedBy(dto.getSubmittedBy());
//        record.setFollowUpRequired(dto.getFollowUpRequired());
//        record.setFollowUpDate(dto.getFollowUpDate());
        record.setPatient(patient);
        record.setHospitalStaff(staff);
    }

    private MedicalRecordsUpdateDTO convertToDTO(MedicalRecords record) {
        MedicalRecordsUpdateDTO dto = new MedicalRecordsUpdateDTO();
        dto.setNotes(record.getNotes());
        dto.setDiagnosis(record.getDiagnosis());
        dto.setTreatment(record.getTreatment());
        dto.setSubmittedAt(record.getSubmittedAt());
        dto.setSubmittedBy(record.getSubmittedBy());
        dto.setPatientId(record.getPatient().getId());
        dto.setHospitalStaffId(record.getHospitalStaff().getId());
        return dto;
    }
}