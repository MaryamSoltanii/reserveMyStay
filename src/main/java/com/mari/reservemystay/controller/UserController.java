package com.mari.reservemystay.controller;

import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.security.UserModel;
import com.mari.reservemystay.services.security.SecurityService;
import com.mari.reservemystay.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private SecurityService securityService;

    @PostMapping
    private Long saveUser(@RequestBody UserModel model) {
        try {
            return service.saveUser(model);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/login-by-otp")
    private Boolean loginByOtp(Integer otp, String username) {
        try {
            String ipAddress = securityService.getClientIp();
            Instant now = Instant.now();
            Date currentDate = Date.from(now);
            return service.loginByOtp(ipAddress, currentDate, otp, username);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/login-by-password")
    private Boolean loginByPassword(String username, String password) {
        try {
            return service.loginByPassword(username, password);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
