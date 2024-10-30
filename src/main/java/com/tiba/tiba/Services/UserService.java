package com.tiba.tiba.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserSignUpRepository userSignUpRepository;


    // Constructor injection
    public UserService(UserSignUpRepository userSignUpRepository) {
        this.userSignUpRepository = userSignUpRepository;

    }

    public com.tiba.tiba.Entities.User sign_upNewUser(com.tiba.tiba.Entities.User user) {

        // Hash the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userSignUpRepository.save(user);
    }
}