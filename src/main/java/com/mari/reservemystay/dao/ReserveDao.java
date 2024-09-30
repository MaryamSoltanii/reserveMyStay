package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.Reserve;
import com.mari.reservemystay.domain.Room;
import com.mari.reservemystay.model.reservation.implement.ReserveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReserveDao extends JpaRepository<Reserve, Long> {
    @Query(name = "ReserveDao.countAvailableRooms", nativeQuery = true)
    Long countAvailableRooms(Long roomId, Date toDate);

    @Query(name = "ReserveDao.checkCancelRooms", nativeQuery = true)
    Boolean isCancelUnreserved(Long reserveId);

    @Query(name = "ReserveDao.validateUniqueCode",nativeQuery = true)
    Integer validateUniqueCode(String code);

    @Query(name = "ReserveDao.reservationList",nativeQuery = true)
    List<ReserveInfo> getUserReserveList(Long userId);

    @Query(name = "ReserveDao.getUniqueCode",nativeQuery = true)
    String getUniqueCode();
}
