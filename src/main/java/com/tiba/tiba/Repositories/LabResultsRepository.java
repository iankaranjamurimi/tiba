package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.LabResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabResultsRepository extends JpaRepository<LabResults, Long> {
    List<LabResults> findByMedicalRecordsId(Long medicalRecordsId);
}
