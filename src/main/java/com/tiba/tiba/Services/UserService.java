package com.tiba.tiba.Services;

import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        // Use the password encoder to match the raw password with the hashed password
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    private final UserSignUpRepository userSignUpRepository;


    // Constructor injection
    public UserService(PasswordEncoder passwordEncoder, UserSignUpRepository userSignUpRepository, UserRepository userRepository, UserRepository userRepository1) {
        this.passwordEncoder = passwordEncoder;
        this.userSignUpRepository = userSignUpRepository;

        this.userRepository = userRepository1;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public com.tiba.tiba.Entities.User sign_upNewUser(com.tiba.tiba.Entities.User user) {

        // Hash the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userSignUpRepository.save(user);
    }

    private com.tiba.tiba.Entities.User convertToDTO(com.tiba.tiba.Entities.User user) {
        com.tiba.tiba.Entities.User dto = new com.tiba.tiba.Entities.User();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        return dto;
    }


}