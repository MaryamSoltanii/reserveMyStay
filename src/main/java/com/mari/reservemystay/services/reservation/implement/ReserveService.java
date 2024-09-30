package com.mari.reservemystay.services.reservation.implement;

import com.mari.reservemystay.model.reservation.implement.ReservationListByUser;
import com.mari.reservemystay.model.reservation.implement.ReservationModel;

import java.util.Optional;


public interface ReserveService {
    Long save(ReservationModel model);

    Long unreserved(Long reserveId);

    Optional<ReservationListByUser> getUserReserveList(Long userId);
}
