package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>  {
    Optional<Hospital> findByHospitalName(String hospitalName);

}
