package com.mari.reservemystay.services.reservation.basic;

import com.mari.reservemystay.model.reservation.basic.LocationModel;
import com.mari.reservemystay.model.reservation.basic.LocationTuple;

import java.util.Optional;

public interface LocationService {

    Long save(LocationModel model);

    Optional<LocationTuple> findById(Long id);
}