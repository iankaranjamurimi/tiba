//package com.tiba.tiba.Entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class ImagingResult {
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String examType; // e.g., X-Ray, MRI, CT Scan, Ultrasound
//
//    private String bodyPart; // e.g., Chest, Head, Abdomen
//
//    private String results;
//
//    private String status; // e.g., PENDING, COMPLETED, REVIEWED
//
//    private LocalDate createdAt;
//
//    private String submittedBy;
//
//    private String imageType; // e.g., JPEG, PNG, DICOM
//
//    @Lob
//    @Column(columnDefinition = "LONGBLOB")
//    private byte[] imageData;
//
//    @ManyToOne
//    @JoinColumn(name = "hospitalStaff_id")
//    private HospitalStaff hospitalStaff;
//
//    @ManyToOne
//    @JoinColumn(name = "patient_id", nullable = false)
//    private User patient;
//
//
//
//
//}
