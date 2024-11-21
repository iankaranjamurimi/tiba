package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.HospitalStaff;
import com.tiba.tiba.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalStaffRepository extends JpaRepository<HospitalStaff, Long> {
    Optional<HospitalStaff> findByIdNumber(Integer idNumber);

    Optional<HospitalStaff> findByUser(User user);
}

