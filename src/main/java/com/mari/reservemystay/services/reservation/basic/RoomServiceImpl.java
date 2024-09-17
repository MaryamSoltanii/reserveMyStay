package com.mari.reservemystay.services.reservation.basic;

import com.mari.reservemystay.dao.CommonDataDao;
import com.mari.reservemystay.dao.HotelDao;
import com.mari.reservemystay.dao.RoomDao;
import com.mari.reservemystay.domain.CommonData;
import com.mari.reservemystay.domain.Hotel;
import com.mari.reservemystay.domain.Room;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.reservation.implement.HotelRoomResponse;
import com.mari.reservemystay.model.reservation.basic.RoomList;
import com.mari.reservemystay.model.reservation.basic.RoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private CommonDataDao commonDataDao;
    @Autowired
    private HotelDao hotelDao;

    @Override
    public Long save(RoomModel model) {
        Room room = new Room();
        room.setCapacity(model.getCapacity());
        room.setPrice(model.getPrice());
        room.setEffectiveDate(new java.sql.Date(model.getEffectiveDate().getTime()));
        room.setVoidDate(new java.sql.Date(model.getVoidDate().getTime()));
        room.setDescription(model.getDescription());
        var hotelId = hotelDao.findById(model.getHotelId()).orElseThrow(() -> new BusinessException("ROM_HTL_NOT_FOUND"));
        room.setHotelId(hotelId);
        var commonDataId = commonDataDao.findById(model.getCommonData()).orElseThrow(() -> new BusinessException("invalid Common Data Id"));
        room.setCommonData(commonDataId);
        roomDao.save(room);
        return room.getId();
    }

    @Override
    public RoomModel update(Long roomId, RoomModel model) {
        var entity = roomDao.findById(roomId).orElseThrow(IllegalArgumentException::new);
        //todo check code
        if (!Objects.equals(entity.getDescription(), model.getDescription())) {
            entity.setDescription(model.getDescription());
        }
        if (!Objects.equals(entity.getCommonData(), model.getCommonData())) {
            var commonData = commonDataDao.findById(model.getCommonData())
                    .orElseThrow(() -> new BusinessException("Invalid Common Data ID"));
            entity.setCommonData(commonData);
        }
        if (!Objects.equals(entity.getCapacity(), model.getCapacity())) {
            entity.setCapacity(model.getCapacity());
        }
        if (!Objects.equals(entity.getEffectiveDate(), model.getEffectiveDate())) {
            entity.setEffectiveDate(model.getEffectiveDate());
        }
        if (!Objects.equals(entity.getVoidDate(), model.getVoidDate())) {
            entity.setVoidDate(model.getVoidDate());
        }
        if (!Objects.equals(entity.getPrice(), model.getPrice())) {
            entity.setPrice(model.getPrice());
        }
        roomDao.save(entity);
        return model;
    }

    @Override
    public Optional<RoomList> loadByHotelId(Long roomId) {
        return roomDao.loadByHotelId(roomId);
    }

    @Override
    public Optional<HotelRoomResponse> findRoomsByHotelId(Long hotelId) {
        List<RoomList> roomLists = roomDao.findRoomsByHotelId(hotelId);
        if (roomLists.isEmpty()) {
            return Optional.empty();
        }
        String hotelName = roomLists.get(0).getHotelName();
        HotelRoomResponse response = new HotelRoomResponse(hotelName, roomLists);
        return Optional.of(response);
    }
}