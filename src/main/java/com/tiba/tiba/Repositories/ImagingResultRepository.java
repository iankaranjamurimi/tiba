package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.ImagingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagingResultRepository extends JpaRepository<ImagingResult, Long> {

}