package com.mari.reservemystay.services.reservation.basic;

import com.mari.reservemystay.dao.LocationDao;
import com.mari.reservemystay.domain.Location;
import com.mari.reservemystay.model.reservation.basic.LocationModel;
import com.mari.reservemystay.model.reservation.basic.LocationTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LocationDao locationDao;

//    @Override
//    public List<LocationModel> findAll() {
//        List<LocationModel> response = new ArrayList<>();
//        var locations = locationDao.findAll();
//        locations.forEach(var -> response.add(LocationModel.builder()
//                .id(var.getId())
//                .name(var.getName())
//                .build()));
//        return response;
//    }


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
