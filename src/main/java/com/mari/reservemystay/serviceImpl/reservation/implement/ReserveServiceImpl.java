package com.mari.reservemystay.serviceImpl.reservation.implement;

import com.mari.reservemystay.dao.*;
import com.mari.reservemystay.domain.Reserve;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.basic.PersonModel;
import com.mari.reservemystay.model.reservation.implement.*;
import com.mari.reservemystay.services.basic.PersonService;
import com.mari.reservemystay.services.reservation.implement.ReserveDetailService;
import com.mari.reservemystay.services.reservation.implement.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private PersonService personService;

    @Autowired
    private ReserveDetailService reserveDetailService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReserveDetailDao reserveDetailDao;

    private Long checkRoomAvailability(Long roomId, Date toDate) {
        return reserveDao.countAvailableRooms(roomId, toDate);
    }

    @Override
    public Long save(ReservationModel model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long roomId = personDao.findById(model.getRoomId()).orElseThrow(() -> new BusinessException(RES_ROOM_NOT_FOUND_EXCEPTION)).getId();
        Long count = checkRoomAvailability(roomId, model.getToDate());
        Long reserveId;
        if (count == 0) {
            reserveId = saveReserve(model, roomId);
            List<Long> personList = saveGuests(model);
            saveReserveDetail(personList, reserveId);
        } else {
            throw new BusinessException(RES_ROOM_RESERVED);
        }
        return reserveId;
    }

    public Long saveReserve(ReservationModel model, Long roomId) {
        Reserve reserve = new Reserve();
        reserve.setIsCancel(0);
        reserve.setToDate(model.getToDate());
        reserve.setFromDate(model.getFromDate());
        reserve.setReserveDate(model.getReserveDate());
        var code = reserveDao.getUniqueCode();
        reserve.setCode(code);
        reserve.setRoomId(roomId);
        Long userId = userDao.findById(model.getUserId()).orElseThrow(() -> new BusinessException(USER_IS_NOT_LOGIN)).getId();
        reserve.setUser(userId);
        reserveDao.save(reserve);
        return reserve.getId();
    }

    private List<Long> saveGuests(ReservationModel model) {
        List<Guestslist> guests = model.getGuests();
        List<Long> personList = new ArrayList<>();
        for (Guestslist guestslist : guests) {
            String nationalCode = guestslist.getNationalCode();
            Long personId = personDao.findByNationalCode(nationalCode);
            if (personId == null) {
               personId = callPersonSave(personList, guestslist, nationalCode);
            }
                personList.add(personId);
        }
        return personList;
    }

    private Long callPersonSave( Guestslist guestslist) {
        int genderValue;
        if ("female".equalsIgnoreCase(guestslist.getGender())) {
            genderValue = 1;
        } else {
            genderValue = 0;
        }
        PersonModel personModel = PersonModel.builder().birthdate(guestslist.getBirthdate())
                .firstname(guestslist.getFirstname())
                .lastname(guestslist.getLastname())
                .mobileNo(guestslist.getMobileNo())
                .fatherName(guestslist.getFatherName())
                .passportNo(guestslist.getPassportNo())
                .nationalCode(guestslist.getNationalCode)
                .gender(genderValue).build();
        return personService.save(personModel).getId();
    }
    private void saveReserveDetail(List<Long> guests, Long reserveId) {
        for (Long guestsList : guests) {
            ReserveDetailModel reserveDetailModel = ReserveDetailModel.builder()
                    .reserveId(reserveId)
                    .personId(guestsList)
                    .build();
            reserveDetailService.save(reserveDetailModel);
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

    @Override
    public Optional<ReservationListByUser> getUserReserveList(Long userId) {
        List<ReserveInfo> reserveInfo = reserveDao.getUserReserveList(userId);
        if (reserveInfo.isEmpty()) {
            return Optional.empty();
        }
        ReservationListByUser response = new ReservationListByUser(reserveInfo);
        return Optional.of(response);

    }
}

