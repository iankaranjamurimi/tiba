package com.tiba.tiba.Services;

//import org.springframework.security.crypto.password.PasswordEncoder;
import com.tiba.tiba.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
   // private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public UserService(UserRepository userRepository/*, PasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    public com.tiba.tiba.Entities.User sign_upNewUser(com.tiba.tiba.Entities.User user) {

        // Hash the password before saving the user
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }
}