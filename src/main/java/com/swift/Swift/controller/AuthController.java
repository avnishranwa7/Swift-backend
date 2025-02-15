package com.swift.Swift.controller;

import com.swift.Swift.model.User;
import com.swift.Swift.payload.LoginDTO;
import com.swift.Swift.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("")
    public void createUserByPhone(@RequestBody LoginDTO loginDTO) {
        authService.createUser(loginDTO);
    }
}
