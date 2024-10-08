package com.mari.reservemystay.serviceImpl.security;

import com.mari.reservemystay.dao.OtpDao;
import com.mari.reservemystay.dao.UserDao;
import com.mari.reservemystay.domain.Otp;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.services.general.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

import static com.mari.reservemystay.exception.BusinessException.USR_NOT_ACTIVE;

@Service
@Transactional
public class OtpServiceImpl implements OtpService {
    @Autowired
    private OtpDao otpDao;

    @Autowired
    private UserDao userDao;

    @Value("${reserve.opt.duration}")
    private int otpDuration;

    private Integer saveOtp(String username, String ipAddress, Boolean isExistsUser) {
        Date lastEndDate = otpDao.getOtpEndDate(username, ipAddress);
        if (lastEndDate <= (LocalDateTime.now().plusMinutes(otpDuration)))//(sysdate + interval '02' minute)
        {
            var otpCode = otpDao.getNewOtp();
            var otp = new Otp();
            otp.setOtp(otpCode);
            otp.setIpAddress(ipAddress);
            if (isExistsUser) {
                otp.setUserId(userDao.loadByUsername(username));
            }
            otp.setStartDate(LocalDateTime.now());
            otp.setEndDate(LocalDateTime.now().plusMinutes(otpDuration));
            otp.setIsUsed(false);
            otpDao.save(otp);
            return otpCode;
        }
        else{
            throw new BusinessException(OTP_IS_VALID);
        }
    }

    @Override
    public Integer sendOtp(String username, String ipAddress) {
        var isExistsUser = userDao.isExistsUser(username);
        if (isExistsUser) {
            var isActiveUser = userDao.isActiveUser(username);
            if (isActiveUser == 0) {
                throw new BusinessException(USR_NOT_ACTIVE);
            } else {
                return saveOtp(username, ipAddress, true);
            }
        } else {
            return saveOtp(username, ipAddress, false);
        }
    }

    @Override
    public Integer validateOtp(String ipAddress, Date currentDate, Integer otpValue) {
        return otpDao.validateOtp(ipAddress, currentDate, otpValue);
    }
}
