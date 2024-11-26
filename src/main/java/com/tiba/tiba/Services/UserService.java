package com.tiba.tiba.Services;

import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return !passwordEncoder.matches(rawPassword, hashedPassword);
    }

    // method to get all users
    public List<com.tiba.tiba.Entities.User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private com.tiba.tiba.Entities.User convertToDTO(com.tiba.tiba.Entities.User user) {
        com.tiba.tiba.Entities.User dto = new com.tiba.tiba.Entities.User();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        dto.setRoles(user.getRoles());
        return dto;
    }
    @Transactional
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Encode the new password before saving
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}
