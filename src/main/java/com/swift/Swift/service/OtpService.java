package com.swift.Swift.service;

import com.swift.Swift.model.User;
import com.swift.Swift.payload.OtpDTO;
import com.swift.Swift.repository.OtpRepository;

import java.security.SecureRandom;

public class OtpService {

    OtpRepository otpRepository;
    SecureRandom random = new SecureRandom();

    public String generateOtp() {
        int otp = random.nextInt(900000) + 100000;
        return String.valueOf(otp);
    }

    public String generateAndSaveOtp(String phoneNo, OtpDTO otpDto) {
        String otp = generateOtp();
        User user = new User();
        user.setOtp(otpDto.getOtp());
        //  user.setExpiryTime(otpDto.getExpiryTime(LocalDateTime.now().plusMinutes(2)));
        otpRepository.save(user);
        return otp;
    }
}
