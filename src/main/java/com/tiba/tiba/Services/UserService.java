package com.tiba.tiba.Services;

import com.tiba.tiba.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserSignUpRepository userSignUpRepository;
    @Autowired
    private UserRepository userRepository;


    // Constructor injection
    public UserService(UserSignUpRepository userSignUpRepository) {
        this.userSignUpRepository = userSignUpRepository;

    }


    public com.tiba.tiba.Entities.User sign_upNewUser(com.tiba.tiba.Entities.User user) {

        // Hash the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userSignUpRepository.save(user);
    }

    public boolean verifyPassword(String password, String password1) {
            return false;
    }
}