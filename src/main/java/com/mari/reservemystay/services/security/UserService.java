package com.mari.reservemystay.services.security;

import com.mari.reservemystay.model.security.UserModel;

import java.util.Date;

public interface UserService {
    Boolean isExistsUser(String username);

    Long isActiveUser(String username);

    Long saveUser(UserModel model);

    Boolean loginByOtp(String ipAddress, Date currentDate, Integer otp, String username);

    Boolean loginByPassword(String username, String password);
}
