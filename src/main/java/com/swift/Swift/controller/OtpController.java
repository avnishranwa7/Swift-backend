package com.swift.Swift.controller;

import com.swift.Swift.payload.OtpDTO;
import com.swift.Swift.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/otp")
public class OtpController {

    OtpService otpService;

    @PostMapping("/generate/{phoneNo}")
    public ResponseEntity<?> generateOtp(@PathVariable String phoneNo, @RequestBody OtpDTO otpDTO) {
        String otp = otpService.generateAndSaveOtp(phoneNo, otpDTO);

        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP generated successfully");
        response.put("OTP", otp);

        return ResponseEntity.ok(response);
    }

    //  @PostMapping("/verify")
//    public ResponseEntity<?> verifyOtp(@RequestParam String mobileNo, @RequestParam String otp){
//        boolean isValidOtp = otpService.verifyOtp(mobileNo, otp);
//
//        if(isValidOtp){
//           return ResponseEntity.ok(Map.of("message", "OTP verified successfully"));
//        } else{
//            return ResponseEntity.badRequest().body(Map.of("message", "Invalid or expired OTP"));
//        }
//    }
}
