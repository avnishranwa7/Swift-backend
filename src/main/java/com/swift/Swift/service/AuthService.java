package com.swift.Swift.service;

import com.swift.Swift.model.User;
import com.swift.Swift.payload.LoginDTO;
import com.swift.Swift.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    SecureRandom random = new SecureRandom();
    @Autowired
    private MongoTemplate mongoTemplate;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public User createUser(LoginDTO loginDTO) {
        User user = new User();
        user.setEmailId(loginDTO.getEmailId());
        user.setMobileNo(loginDTO.getMobileNo());
        return authRepository.save(user);
    }

    public Optional<User> checkIfUserExists(LoginDTO loginDTO) {

        Optional<User> user = Optional.of(new User());
        if (loginDTO.getEmailId() != null) {
            user = authRepository.findByEmailId(loginDTO.getEmailId());
        } else if (loginDTO.getMobileNo() != null) {
            user = authRepository.findUserByMobileNo(loginDTO.getMobileNo());
        }
        if (user.isEmpty())
            user = Optional.of(createUser(loginDTO));
        return user;
    }
}
