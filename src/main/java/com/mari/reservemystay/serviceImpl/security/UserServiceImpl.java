package com.mari.reservemystay.serviceImpl.security;

import com.mari.reservemystay.dao.PersonDao;
import com.mari.reservemystay.dao.UserDao;
import com.mari.reservemystay.domain.Person;
import com.mari.reservemystay.domain.User;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.security.UserModel;
import com.mari.reservemystay.services.general.OtpService;
import com.mari.reservemystay.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static com.mari.reservemystay.exception.BusinessException.NOT_VALID_OTP;
import static com.mari.reservemystay.exception.BusinessException.USR_NOT_VALID;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private OtpService otpService;

    @Autowired
    private PersonDao personDao;

    @Override
    public Boolean isExistsUser(String username) {
        return userDao.isExistsUser(username);
    }

    @Override
    public Long isActiveUser(String username) {
        return userDao.isActiveUser(username);
    }

    @Override
    public Long saveUser(UserModel model) {
        var user = new User();
        Long personId = personDao.findByMobileNo(model.getUsername());
        if (personId != null) {
            user.setPersonId(personId);
        }
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        user.setIsActive(model.getIsActive());
        user.setRegisterDate(LocalDate.now());
        userDao.save(user);
        return user.getId();
    }

    @Override
    public Boolean loginByOtp(String ipAddress, Date currentDate, Integer otp, String username) {
        var isValidOtp = otpService.validateOtp(ipAddress, currentDate, otp);
        if (isValidOtp == 0) {
            throw new BusinessException(NOT_VALID_OTP);
        } else {
            return isExistsUser(username);
        }
    }

    @Override
    public Boolean loginByPassword(String username, String password) {
        var isValidUserPass = userDao.ValidateUserPass(username, password);
        if (isValidUserPass == 0) {
            throw new BusinessException(USR_NOT_VALID);
        } else {
            return true;
        }
    }
}
