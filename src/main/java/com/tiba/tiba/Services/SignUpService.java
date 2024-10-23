package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.SignUpDTO;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void registerUser(@Valid SignUpDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstname());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastname());

        Patient patient = new Patient();
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setIdNumber(request.getIdNumber());
        patient.setContactNumber(request.getContactNumber());

        Set<User.UserRole> roles = new HashSet<>();
        for (User.UserRole roleName : request.getRoles()) {
            roles.add(User.UserRole.valueOf(String.valueOf(roleName))); // Convert string to UserRole enum
        }
        user.setRoles(roles);

        user.setPatient(patient);
        patient.setUser(user);

        userRepository.save(user);
    }
}
