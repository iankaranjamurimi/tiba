
package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.PasswordUpdateDTO;
import com.tiba.tiba.Models.ApiResponse;
import com.tiba.tiba.Services.PasswordUpdateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
public class PasswordUpdateController {

    private final PasswordUpdateService passwordUpdateService;

    public PasswordUpdateController(PasswordUpdateService passwordUpdateService) {
        this.passwordUpdateService = passwordUpdateService;
    }

    @PutMapping("/user/password/{userId}")
    public ResponseEntity<Object> updatePassword(
            @PathVariable Long userId,
            @Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        try {
            passwordUpdateService.updatePassword(userId, passwordUpdateDTO);
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Password updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
}
