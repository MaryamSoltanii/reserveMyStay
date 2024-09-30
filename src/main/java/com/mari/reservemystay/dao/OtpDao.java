package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface OtpDao extends JpaRepository<Otp, Long> {
    @Query(name = "OptDao.validateOtp",nativeQuery = true)
    Integer validateOtp(String ipAddress, Date currentDate,Integer otpValue);

    @Query(name = "OptDao.GetOtp",nativeQuery = true)
    Integer getNewOtp();
}
