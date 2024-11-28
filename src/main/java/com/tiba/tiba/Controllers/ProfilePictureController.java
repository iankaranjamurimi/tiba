package com.tiba.tiba.Controllers;

import com.tiba.tiba.Services.ProfilePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/open")
@RequiredArgsConstructor
public class ProfilePictureController {
    private final ProfilePictureService profilePictureService;


    @PostMapping("/{userId}/profile-picture")
    public ResponseEntity<Map<String, Object>> uploadProfilePicture(
            @PathVariable Long userId,
            @RequestPart("file") MultipartFile file
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            String profilePictureUrl = profilePictureService.uploadProfilePicture(userId, file);
            response.put("success", true);
            response.put("message", "Profile Picture uploaded successfully");
            response.put("data", profilePictureUrl);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Failed to upload profile picture");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @GetMapping("/display/profile-pic/{userId}")
    public ResponseEntity<Map<String, Object>> getProfilePicture(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            String profilePictureUrl = profilePictureService.getProfilePicture(userId);
            response.put("success", true);
            response.put("message", "Profile picture retrieved successfully");
            response.put("data", profilePictureUrl);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to retrieve profile picture");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }


    @DeleteMapping("/{userId}/delete-profile-picture")
    public ResponseEntity<Map<String, Object>> deleteProfilePicture(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            profilePictureService.deleteProfilePicture(userId);
            response.put("success", true);
            response.put("message", "Profile Picture deleted successfully");
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Failed to delete profile picture");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
}


//@PostMapping("/{userId}/profile-picture")
//public ResponseEntity<Map<String, Object>> uploadProfilePicture(
//        @PathVariable Long userId,
//        @RequestPart("file") MultipartFile file
//) {
//    Map<String, Object> response = new HashMap<>();
//    try {
//        profilePictureService.uploadProfilePicture(userId, file);
//        response.put("success", true);
//        response.put("message", "Profile Picture uploaded successfully");
//        return ResponseEntity.ok(response);
//    } catch (IOException e) {
//        response.put("success", false);
//        response.put("message", "Failed to upload profile picture");
//        response.put("error", e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(response);
//    }
//}
