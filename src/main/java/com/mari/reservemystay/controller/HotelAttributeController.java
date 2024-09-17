package com.mari.reservemystay.controller;

import com.mari.reservemystay.model.reservation.basic.HotelAttributeModel;
import com.mari.reservemystay.services.reservation.basic.HotelAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotel-attribute")
public class HotelAttributeController {
    @Autowired
    private HotelAttributeService hotelAttributeService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody HotelAttributeModel model){
        return ResponseEntity.ok(hotelAttributeService.save(model));
    }
}
