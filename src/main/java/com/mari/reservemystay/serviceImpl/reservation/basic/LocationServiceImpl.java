package com.mari.reservemystay.serviceImpl.reservation.basic;

import com.mari.reservemystay.dao.LocationDao;
import com.mari.reservemystay.domain.Location;
import com.mari.reservemystay.model.reservation.basic.LocationModel;
import com.mari.reservemystay.model.reservation.basic.LocationTuple;
import com.mari.reservemystay.services.reservation.basic.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Override
    public Long save(LocationModel model) {
        var location = new Location();
        location.setCode(model.getCode());
        location.setName(model.getName());
        location.setParentId(model.getParentId());
        locationDao.save(location);
        return location.getId();
    }

    @Override
    public Optional<LocationTuple> findById(Long id){
        return locationDao.loadById(id);
    }
}
