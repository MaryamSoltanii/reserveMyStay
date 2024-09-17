package com.mari.reservemystay.services.reservation.basic;

import com.mari.reservemystay.model.reservation.basic.HotelModel;
import com.mari.reservemystay.model.reservation.basic.HotelTuple;

import java.util.Optional;

public interface HotelService {

    Long save(HotelModel model);

    Optional<HotelTuple> hotelWithLocation();

    Optional<HotelTuple> findById(Long id);

    Long deleteHotelById(Long id);
}
