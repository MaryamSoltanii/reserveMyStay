package com.mari.reservemystay.serviceImpl.basic;


import com.mari.reservemystay.dao.CommonDataDao;
import com.mari.reservemystay.dao.HotelAttributeDao;
import com.mari.reservemystay.dao.HotelDao;
import com.mari.reservemystay.domain.CommonData;
import com.mari.reservemystay.domain.Hotel;
import com.mari.reservemystay.domain.HotelAttribute;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.reservation.basic.HotelAttributeModel;
import com.mari.reservemystay.services.reservation.basic.HotelAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.mari.reservemystay.exception.BusinessException.HOA_HTL_NOT_FOUND;
import static com.mari.reservemystay.exception.BusinessException.RED_RES_NOT_FOUND;

@Service
@Transactional
public class HotelAttributeServiceImpl implements HotelAttributeService {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private CommonDataDao commonDataDao;

    @Autowired
    private HotelAttributeDao hotelAttributeDao;

    @Override
    public Long save(HotelAttributeModel model) {
        HotelAttribute hotelAttribute = new HotelAttribute();
        hotelAttribute.setEffectiveDate(new java.sql.Date(model.getEffectiveDate().getTime()));
        hotelAttribute.setVoidDate(new java.sql.Date(model.getVoidDate().getTime()));
        var commonDataId = commonDataDao.findById(model.getCommonDataId())
                .orElseThrow(() -> new BusinessException("invalid common data")).getId();
        hotelAttribute.setFk_cod(commonDataId);
        var hotelId = hotelDao.findById(model.getHotelId()).orElseThrow(() -> new BusinessException(HOA_HTL_NOT_FOUND)).getId();
        hotelAttribute.setFk_htl(hotelId);
        hotelAttributeDao.save(hotelAttribute);
        return hotelAttribute.getId();
    }
}