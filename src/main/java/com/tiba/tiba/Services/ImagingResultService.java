package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.ImagingResultDTO;
import com.tiba.tiba.Entities.ImagingResult;
import com.tiba.tiba.Repositories.ImagingResultRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class ImagingResultService {
    private final ImagingResultRepository imagingResultRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ImagingResultService(
            ImagingResultRepository imagingResultRepository,
            CloudinaryService cloudinaryService
    ) {
        this.imagingResultRepository = imagingResultRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public ImagingResultDTO createImagingResult(ImagingResultDTO imagingResultDTO) throws IOException {
        MultipartFile file = imagingResultDTO.getImageFile();
        Map uploadResult = cloudinaryService.uploadImage(file);

        ImagingResult imagingResult = new ImagingResult();
        BeanUtils.copyProperties(imagingResultDTO, imagingResult);

        imagingResult.setImagePublicId((String) uploadResult.get("public_id"));
        imagingResult.setImageUrl((String) uploadResult.get("secure_url"));

        if (imagingResult.getCreatedAt() == null) {
            imagingResult.setCreatedAt(LocalDate.now());
        }

        ImagingResult savedResult = imagingResultRepository.save(imagingResult);
        ImagingResultDTO savedDTO = new ImagingResultDTO();
        BeanUtils.copyProperties(savedResult, savedDTO);

        return savedDTO;
    }

    public Optional<ImagingResultDTO> getImagingResultById(Long id) {
        return imagingResultRepository.findById(id)
                .map(imagingResult -> {
                    ImagingResultDTO dto = new ImagingResultDTO();
                    BeanUtils.copyProperties(imagingResult, dto);
                    return dto;
                });
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