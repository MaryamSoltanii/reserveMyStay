package com.mari.reservemystay.services.reservation.basic;


import com.mari.reservemystay.dao.HotelDao;
import com.mari.reservemystay.dao.LocationDao;
import com.mari.reservemystay.domain.Hotel;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.reservation.basic.HotelModel;
import com.mari.reservemystay.model.reservation.basic.HotelTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.mari.reservemystay.exception.BusinessException.*;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private LocationDao locationDao;

    @Override
    public Optional<HotelTuple> hotelWithLocation() {
        return hotelDao.hotelWithLocation();
    }

    @Override
    public Optional<HotelTuple> findById(Long id) {
        return hotelDao.loadById(id);
    }

    @Override
    public Long save(HotelModel model) {
        Hotel hotel = new Hotel();
        hotel.setName(model.getName());
        hotel.setCode(model.getCode());
        hotel.setEnglish_name(model.getEnglishName());
        var locationId = locationDao.findById(model.getLocationId()).orElseThrow(() -> new BusinessException(HTL_LOC_NOT_FOUND));
        hotel.setLocation((locationId));
        hotelDao.save(hotel);
        return hotel.getId();
    }
    public Long deleteHotelById(Long id) {
        try {
            if (hotelDao.existsById(id)) {
                hotelDao.deleteById(id);
                return id;
            } else {
                throw new BusinessException(HTL_NOT_FOUND);
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
