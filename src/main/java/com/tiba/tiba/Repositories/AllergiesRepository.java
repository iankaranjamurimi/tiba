package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Allergies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergiesRepository extends JpaRepository<Allergies, Long> {
}
