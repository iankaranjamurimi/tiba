package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.ImagingResultDTO;
import com.tiba.tiba.Entities.ImagingResult;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.ImagingResultRepository;
import com.tiba.tiba.Repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImagingResultService {
    private final ImagingResultRepository imagingResultRepository;
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;

    @Autowired
    public ImagingResultService(
            ImagingResultRepository imagingResultRepository,
            CloudinaryService cloudinaryService,
            UserRepository userRepository) {
        this.imagingResultRepository = imagingResultRepository;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
    }

    public ImagingResultDTO createImagingResult(ImagingResultDTO imagingResultDTO) throws IOException {
        MultipartFile file = imagingResultDTO.getImageFile();
        Map uploadResult = cloudinaryService.uploadImage(file);

        ImagingResult imagingResult = new ImagingResult();
        BeanUtils.copyProperties(imagingResultDTO, imagingResult);

        // Retrieve the User entity based on userId
        User user = userRepository.findById(imagingResultDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User  not found"));

        imagingResult.setUser (user); // Set the User entity

        imagingResult.setImagePublicId((String) uploadResult.get("public_id"));
        imagingResult.setImageUrl((String) uploadResult.get("secure_url"));

        if (imagingResult.getCreatedAt() == null) {
            imagingResult.setCreatedAt(LocalDate.now());
            imagingResult.setExamType((String) uploadResult.get("exam_type"));
            imagingResult.setBodyPart((String) uploadResult.get("body_part"));
            imagingResult.setResults((String) uploadResult.get("results"));


        }

        ImagingResult savedResult = imagingResultRepository.save(imagingResult);
        ImagingResultDTO savedDTO = new ImagingResultDTO();
        BeanUtils.copyProperties(savedResult, savedDTO);

        return savedDTO;
    }

    public List<ImagingResultDTO> getImagingResultsByUser(Long userId) {
        // First verify if the user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get all imaging results for the user
        List<ImagingResult> results = imagingResultRepository.findByUser(user);

        // Convert all results to DTOs
        return results.stream()
                .map(result -> {
                    ImagingResultDTO dto = new ImagingResultDTO();
                    BeanUtils.copyProperties(result, dto);
                    dto.setUserId(userId); // Ensure userId is set in the DTO
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void deleteImagingResult(Long id) throws IOException {
        ImagingResult imagingResult = imagingResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imaging result not found"));

        if (imagingResult.getImagePublicId() != null) {
            cloudinaryService.deleteImage(imagingResult.getImagePublicId());
        }

        imagingResultRepository.delete(imagingResult);
    }
}
