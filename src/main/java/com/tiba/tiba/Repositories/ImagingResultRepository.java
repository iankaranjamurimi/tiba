//package com.tiba.tiba.Repositories;
//
//import com.tiba.tiba.Entities.ImagingResult;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public interface ImagingResultRepository extends JpaRepository<ImagingResult, Integer> {
//    List<ImagingResult> findByPatientId(Long patientId);
//    List<ImagingResult> findByTechnicianId(Long technicianId);
//    List<ImagingResult> findByExamType(String examType);
//    List<ImagingResult> findByPatientIdAndExamDateBetween(Long patientId, LocalDateTime startDate, LocalDateTime endDate);
//    List<ImagingResult> findByStatus(String status);
//}
//
//
//
//
