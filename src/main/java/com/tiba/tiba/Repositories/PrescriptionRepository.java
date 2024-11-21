package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByUser_Id(Long userId);
}

