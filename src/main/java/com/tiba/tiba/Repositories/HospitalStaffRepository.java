package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.HospitalStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalStaffRepository extends JpaRepository<HospitalStaff, Long> {
    Optional<HospitalStaff> findById(Long Id);


    Optional<HospitalStaff> findByIdNumber(Integer idNumber);
}

