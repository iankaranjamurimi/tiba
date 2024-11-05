package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.MedicalRecords;
import com.tiba.tiba.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, Long> {

Optional<MedicalRecords> findByPatientId(Long patientId);
List<MedicalRecords> findByPatient(Patient patient);
}