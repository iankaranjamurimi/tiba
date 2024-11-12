package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Allergies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergiesRepository extends JpaRepository<Allergies, Long> {
    List<Allergies> findByUserId(Long userId);

}
