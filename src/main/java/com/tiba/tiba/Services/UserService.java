package com.tiba.tiba.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.tiba.tiba.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    // Constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public com.tiba.tiba.Entities.User sign_upNewUser(com.tiba.tiba.Entities.User user) {

        // Hash the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}