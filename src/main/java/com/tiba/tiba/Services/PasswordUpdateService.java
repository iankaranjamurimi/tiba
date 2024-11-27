
package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.PasswordUpdateDTO;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PasswordUpdateService {

    private final UserSignUpRepository userSignUpRepository;

    private final PasswordEncoder passwordEncoder;

    public PasswordUpdateService(UserSignUpRepository userSignUpRepository, PasswordEncoder passwordEncoder) {
        this.userSignUpRepository = userSignUpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void updatePassword(Long userId, PasswordUpdateDTO passwordUpdateDTO) {
        User user = userSignUpRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Hash the provided current password and compare with stored hash
        String currentPasswordHash = passwordEncoder.encode(passwordUpdateDTO.getCurrentPassword());

        // Since we can't directly compare hashes (as BCrypt generates different hashes for the same password),
        // we use the matches method which handles the comparison correctly
        if (!passwordEncoder.matches(passwordUpdateDTO.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        // Verify password confirmation
        if (!passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getConfirmPassword())) {
            throw new RuntimeException("New password and confirmation do not match");
        }


        // Ensure new password is different from current password
        if (passwordEncoder.matches(passwordUpdateDTO.getNewPassword(), user.getPassword())) {
            throw new RuntimeException("New password must be different from current password");
        }

        // Hash and update the new password
        String newPasswordHash = passwordEncoder.encode(passwordUpdateDTO.getNewPassword());
        user.setPassword(newPasswordHash);
        userSignUpRepository.save(user);
    }


}
