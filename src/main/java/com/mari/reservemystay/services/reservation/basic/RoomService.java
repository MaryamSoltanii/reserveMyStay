package com.mari.reservemystay.services.reservation.basic;

import com.mari.reservemystay.model.reservation.implement.HotelRoomResponse;
import com.mari.reservemystay.model.reservation.basic.RoomList;
import com.mari.reservemystay.model.reservation.basic.RoomModel;

import java.util.Optional;

public interface RoomService {
    Long save(RoomModel model);

    Optional<RoomList> loadByHotelId(Long roomId);

    Optional<HotelRoomResponse> findRoomsByHotelId(Long hotelId);

    RoomModel update(Long roomId,RoomModel model);
}
