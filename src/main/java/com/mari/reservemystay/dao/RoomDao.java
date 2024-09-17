package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.Room;
import com.mari.reservemystay.model.reservation.basic.RoomList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomDao extends JpaRepository<Room, Long> {
    @Query (name = "RoomDao.loadByHotelId", nativeQuery = true)
    Optional<RoomList> loadByHotelId(Long roomId);

    @Query (name = "RoomDao.findRoomsByHotelId", nativeQuery = true)
    List<RoomList> findRoomsByHotelId(Long hotelId);
}
