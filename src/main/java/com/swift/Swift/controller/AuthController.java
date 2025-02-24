package com.swift.Swift.controller;

import com.swift.Swift.payload.LoginDTO;
import com.swift.Swift.payload.OtpDTO;
import com.swift.Swift.service.AuthService;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.twilio.example.ValidationExample.ACCOUNT_SID;
import static com.twilio.example.ValidationExample.AUTH_TOKEN;

@RestController
@RequestMapping("/login")
@Slf4j
public class AuthController {
    LoginDTO loginDTO = new LoginDTO();
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/generateOTP")
    public ResponseEntity<String> generateOTP(@RequestBody OtpDTO otpDTO) {

        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));

        Verification verification = Verification.creator(
                        "VAdbe86a72659ac614da6bd51644854c2a",
                        otpDTO.getMobileNo(),
                        "sms")
                .create();

        log.info("OTP has been successfully generated, and awaits your verification {}", LocalDateTime.now());

        return new ResponseEntity<>("Your OTP has been sent to your verified phone number", HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> verifyUserOTP(@RequestBody OtpDTO otpDTO) throws Exception {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(
                            "VAdbe86a72659ac614da6bd51644854c2a")
                    .setTo(otpDTO.getMobileNo())
                    .setCode(otpDTO.getOtp())
                    .create();

            if (!verificationCheck.getValid()) {
                return new ResponseEntity<>("Incorrect OTP...", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Verification failed....", HttpStatus.BAD_REQUEST);
        }
        loginDTO.setMobileNo(otpDTO.getMobileNo());
        return new ResponseEntity<>(authService.checkIfUserExists(loginDTO), HttpStatus.OK);
    }
}
