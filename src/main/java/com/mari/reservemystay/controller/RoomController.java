package com.mari.reservemystay.controller;

import com.mari.reservemystay.model.reservation.implement.HotelRoomResponse;
import com.mari.reservemystay.model.reservation.basic.RoomList;
import com.mari.reservemystay.model.reservation.basic.RoomModel;
import com.mari.reservemystay.services.reservation.basic.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody RoomModel model) {
        return ResponseEntity.ok(service.save(model));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomList> loadByHotelId(@PathVariable Long roomId) {
        Optional<RoomList> roomList = service.loadByHotelId(roomId);
        return roomList.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{roomId}")
    public Long update(@PathVariable Long roomId, @RequestBody RoomModel model) {
        RoomModel updatedRoom = service.update(roomId, model);
        return updatedRoom.getId();
    }

    @GetMapping("/hotel-room/{hotelId}")
    public ResponseEntity<HotelRoomResponse> findRoomsByHotelId(@PathVariable Long hotelId) {
        Optional<HotelRoomResponse> hotelRoomResponse = service.findRoomsByHotelId(hotelId);
        return hotelRoomResponse.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
