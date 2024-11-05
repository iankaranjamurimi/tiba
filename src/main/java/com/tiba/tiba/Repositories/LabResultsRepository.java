package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.LabResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabResultsRepository extends JpaRepository<LabResults, Long> {
}
