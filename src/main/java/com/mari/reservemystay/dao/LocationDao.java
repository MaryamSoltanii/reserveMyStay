package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.Location;
import com.mari.reservemystay.model.reservation.basic.LocationTuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocationDao extends JpaRepository<Location, Long> {

    @Query(value = "select s.name , 'ssss' as parentName from tb_location s where s.name = :name" , nativeQuery = true)
    Optional<LocationTuple> loadByNameNative(String name);

    @Query(name = "LocationDao.loadLocationById", nativeQuery = true)
    Optional<LocationTuple> loadById(Long id);
}