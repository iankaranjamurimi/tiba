package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    // Find all prescriptions for a user
//    List<Prescription> findByUserId(Long userId);
    List<Prescription> findByUser_Id(Long userId);

}

