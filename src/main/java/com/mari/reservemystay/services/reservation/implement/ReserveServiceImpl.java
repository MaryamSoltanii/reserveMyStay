package com.mari.reservemystay.services.reservation.implement;

import com.mari.reservemystay.dao.PersonDao;
import com.mari.reservemystay.dao.ReserveDao;
import com.mari.reservemystay.dao.RoomDao;
import com.mari.reservemystay.domain.Reserve;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.reservation.implement.ReserveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static com.mari.reservemystay.exception.BusinessException.*;

@Service
@Transactional
public class ReserveServiceImpl implements ReserveService {
    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RoomDao roomDao;

    private Long checkRoomAvailability(Long roomId, Date toDate) {
        return reserveDao.countAvailableRooms(roomId, toDate);
    }

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    //todo generate uuid from db
    private String checkCodeUnique() {
        int maxAttempts = 10;
        int attempts = 0;

        while (attempts < maxAttempts) {
            var code2 = UUID.randomUUID().toString().substring(0, 8);
            var code = generateRandomString(8);
            var isCodeUnique = reserveDao.validateUniqueCode(code);

            if (isCodeUnique == 0) {
                return code;
            }
            attempts++;
        }
        throw new BusinessException("Failed to generate a unique code after " + maxAttempts + " attempts.");
    }

    @Override
    public Long save(ReserveModel model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var room = roomDao.findById(model.getRoomId()).orElseThrow(() -> new BusinessException(RES_ROOM_NOT_FOUND_EXCEPTION));
        Long count = checkRoomAvailability(room.getId(), model.getToDate());
        if (count == 0) {
            Reserve reserve = new Reserve();
            reserve.setIsCancel(model.getIsCancel());
            reserve.setToDate(model.getToDate());
            reserve.setFromDate(model.getFromDate());
            reserve.setReserveDate(model.getReserveDate());
            reserve.setIsDelivery(model.getIsDelivery());
            var code = checkCodeUnique();
            reserve.setCode(code);
            var person = personDao.findById(model.getPrsId()).orElseThrow(() -> new BusinessException((RES_PERSON_NOT_FOUND_EXCEPTION)));
            reserve.setPersonId(person);
            reserve.setRoomId(room);
            reserveDao.save(reserve);
            return reserve.getId();
        } else {
            throw new BusinessException(RES_ROOM_RESERVED);
        }

    }

    public Boolean checkReserveIsCancel(Long reserveId) {
        return reserveDao.isCancelUnreserved(reserveId);
    }

    @Override
    public Long unreserved(Long reserveId) {
        var entity = reserveDao.findById(reserveId).orElseThrow(() -> new BusinessException(RES_RESERVATION_NOT_FOUND));
        Boolean isCancel = checkReserveIsCancel(reserveId);
        if (isCancel) {
            throw new BusinessException(RES_RESERVATION_CANCEL_ERROR);
        } else {
            entity.setIsCancel(0);
            //LocalDate currentDate = LocalDate.now();
            Instant now = Instant.now();
            Date currentDate = Date.from(now);
            entity.setCancelDate(currentDate);
            reserveDao.save(entity);
            return entity.getId();
        }
    }

}

