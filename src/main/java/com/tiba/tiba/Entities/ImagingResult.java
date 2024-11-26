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
    private String examType;
    private String bodyPart;
    private String results;
    private LocalDate createdAt;
    private String submittedBy;
    // Cloudinary fields
    private String imagePublicId;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
//  examType: X-Ray, MRI, CT Scan, Ultrasound