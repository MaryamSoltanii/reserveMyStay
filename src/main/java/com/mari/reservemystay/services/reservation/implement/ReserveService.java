package com.mari.reservemystay.services.reservation.implement;

import com.mari.reservemystay.model.reservation.implement.ReservationModel;


public interface ReserveService {
    Long save(ReservationModel model);

    Long unreserved(Long reserveId);
}
