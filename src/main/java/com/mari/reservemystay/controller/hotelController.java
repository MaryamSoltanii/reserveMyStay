package com.mari.reservemystay.controller;

import com.mari.reservemystay.model.reservation.basic.HotelModel;
import com.mari.reservemystay.model.reservation.basic.HotelTuple;
import com.mari.reservemystay.services.reservation.basic.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class hotelController {
    @Autowired
    private HotelService service;

    @GetMapping("/list-of-hotels")
    public ResponseEntity<HotelTuple> hotelWithLocation() {
        Optional<HotelTuple> hotelTuple = service.hotelWithLocation();
        return hotelTuple.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelTuple> getById(@PathVariable Long id) {
        Optional<HotelTuple> hotelTuple = service.findById(id);
        return hotelTuple.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody HotelModel model){
        return ResponseEntity.ok(service.save(model));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteHotel(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteHotelById(id));
    }
}