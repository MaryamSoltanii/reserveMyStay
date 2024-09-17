package com.mari.reservemystay.services.general;

import java.util.Date;

public interface OtpService {
    Integer sendOtp(String username, String ipAddress);

    public Integer validateOtp(String ipAddress, Date currentDate, Integer otpValue);
}
