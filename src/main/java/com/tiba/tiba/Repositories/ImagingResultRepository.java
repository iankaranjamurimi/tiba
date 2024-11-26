package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.ImagingResult;
import com.tiba.tiba.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagingResultRepository extends JpaRepository<ImagingResult, Long> {
    List<ImagingResult> findByUser(User user);
}