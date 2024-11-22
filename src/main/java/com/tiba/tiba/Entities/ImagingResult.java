package com.tiba.tiba.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class ImagingResult {
    @Id
    @GeneratedValue
    private Long id;
    private String examType; // X-Ray, MRI, CT Scan, Ultrasound
    private String bodyPart; // Chest, Head, Abdomen
    private String results;
    private LocalDate createdAt;
    private String submittedBy;
    // Cloudinary-specific fields
    private String imagePublicId; // Cloudinary's public ID for the image
    private String imageUrl; // URL of the uploaded image
}
