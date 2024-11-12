package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VitalsRepository extends JpaRepository<Vitals, Long> {

//    List<Vitals> findByUserId(Long userId);

    Optional<Vitals> findByUserId(Long userId);
}
