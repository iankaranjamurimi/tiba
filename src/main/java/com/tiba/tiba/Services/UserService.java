package com.tiba.tiba.Services;

import com.tiba.tiba.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        // Use the password encoder to match the raw password with the hashed password
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    private final UserSignUpRepository userSignUpRepository;


    // Constructor injection
    public UserService(PasswordEncoder passwordEncoder, UserSignUpRepository userSignUpRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userSignUpRepository = userSignUpRepository;

    }


    public com.tiba.tiba.Entities.User sign_upNewUser(com.tiba.tiba.Entities.User user) {

        // Hash the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userSignUpRepository.save(user);
    }


}