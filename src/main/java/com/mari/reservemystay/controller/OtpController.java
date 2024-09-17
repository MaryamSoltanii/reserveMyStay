package com.mari.reservemystay.controller;

import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.services.general.OtpService;
import com.mari.reservemystay.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class OtpController {
    @Autowired
    private OtpService otpService;
    @Autowired
    private SecurityService securityService;


    @PostMapping
    private Integer saveOtp(@RequestBody String username) {
        try {
            String ipAddress = securityService.getClientIp();
            //String ipAddress = request.getHeader("X-Forwarded-For");;//request.getRemoteAddr();
            return otpService.sendOtp(username, ipAddress);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
