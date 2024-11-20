package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.MedicalRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, Long> {
    List<MedicalRecords> findByUserId(Long userId);

//    Optional<MedicalRecords> findByuserId(Long userId);

}
