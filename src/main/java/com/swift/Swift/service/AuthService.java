package com.swift.Swift.service;

import com.swift.Swift.model.User;
import com.swift.Swift.payload.LoginDTO;
import com.swift.Swift.repository.AuthRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void createUser(LoginDTO loginDTO) {
        User user = new User();
        user.setEmailId(loginDTO.getEmailId());
        user.setMobileNo(loginDTO.getMobileNo());
        authRepository.save(user);
    }
}
