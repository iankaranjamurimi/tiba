package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.HospitalStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalStaffRepository extends JpaRepository<HospitalStaff, Integer> {


}

