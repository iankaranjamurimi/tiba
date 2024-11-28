package com.tiba.tiba.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfilePictureService {
    private final Cloudinary cloudinary;
    private final UserRepository userRepository;


    public String uploadProfilePicture(Long userId, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload empty file");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Upload the image file to Cloudinary
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "profile_pictures",
                        "public_id", "user_" + userId
                ));

        // Get the secure URL from cloudinary
        String profilePictureUrl = (String) uploadResult.get("secure_url");

        // saving the url
        user.setProfilePictureUrl(profilePictureUrl);
        userRepository.save(user);

        return profilePictureUrl;
    }

    public String getProfilePicture(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getProfilePictureUrl() == null) {
            return "";
        }

        return user.getProfilePictureUrl();
    }

    public void deleteProfilePicture(Long userId) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getProfilePictureUrl() != null) {
            // Extract the public_id from Cloudinary URL
            String publicId = "profile_pictures/user_" + userId;

            // Delete from Cloudinary
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

            // Clear URL from user
            user.setProfilePictureUrl(null);
            userRepository.save(user);
        }
    }
}
