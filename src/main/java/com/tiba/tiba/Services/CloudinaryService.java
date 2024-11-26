package com.tiba.tiba.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    // Upload image to Cloudinary
    public Map uploadImage(MultipartFile file) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "medical_imaging/",
                        "resource_type", "image"
                )
        );
    }

    // Delete image from Cloudinary
    public void deleteImage(String publicId) throws IOException {
                cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
