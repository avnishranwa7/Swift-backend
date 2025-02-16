package com.swift.Swift.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Users")
@Getter
@Setter
public class User {
    private String mobileNo;
    private String emailId;
    private String otp;
    private LocalDateTime expiryTime;
}
