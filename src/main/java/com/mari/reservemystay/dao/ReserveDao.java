package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReserveDao extends JpaRepository<Reserve, Long> {
    @Query(name = "ReserveDao.countAvailableRooms", nativeQuery = true)
    Long countAvailableRooms(Long roomId, Date toDate);

    @Query(name = "ReserveDao.checkCancelRooms", nativeQuery = true)
    Boolean isCancelUnreserved(Long reserveId);

    @Query(name = "ReserveDao.validateUniqueCode",nativeQuery = true)
    Integer validateUniqueCode(String code);
}
