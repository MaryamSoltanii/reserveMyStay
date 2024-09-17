package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.Hotel;
import com.mari.reservemystay.model.reservation.basic.HotelTuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HotelDao extends JpaRepository<Hotel, Long> {
    @Query(value = "select htl.name as hotelName, htl.code as code, htl.id as id from tb_hotel htl " +
            "join tb_location loc on loc.id = htl.fk_loc " +
            "where htl.id = 1", nativeQuery = true)
    Optional<HotelTuple> hotelWithLocation();

    @Query(name = "HotelDao.loadHotelById" ,nativeQuery = true)
    Optional<HotelTuple> loadById(Long id);

}
