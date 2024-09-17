package com.mari.reservemystay.services.reservation.implement;

import com.mari.reservemystay.model.reservation.implement.ReserveModel;


public interface ReserveService {
    Long save(ReserveModel model);

    Long unreserved(Long reserveId);
}
