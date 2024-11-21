package com.tiba.tiba.Controllers;

import com.tiba.tiba.Services.ProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/open")
public class ProfilePictureController {
    private final ProfilePictureService profilePictureService;

    @Autowired
    public ProfilePictureController(ProfilePictureService profilePictureService) {
        this.profilePictureService = profilePictureService;
    }

    @PostMapping("/{userId}/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(
            @PathVariable Long userId,
            @RequestPart("file") MultipartFile file
    ) {
        try {
            String profilePictureUrl = profilePictureService.uploadProfilePicture(userId, file);
            return ResponseEntity.ok(profilePictureUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload profile picture");
        }
    }

    @DeleteMapping("/{userId}/delete-profile-picture")
    public ResponseEntity<Void> deleteProfilePicture(@PathVariable Long userId) {
        try {
            profilePictureService.deleteProfilePicture(userId);
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
