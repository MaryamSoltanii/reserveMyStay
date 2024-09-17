package com.mari.reservemystay.controller;

import com.mari.reservemystay.model.reservation.basic.LocationModel;
import com.mari.reservemystay.model.reservation.basic.LocationTuple;
import com.mari.reservemystay.services.reservation.basic.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService service;

    @GetMapping("/{id}")
    public ResponseEntity<LocationTuple> getById(@PathVariable Long id) {
        Optional<LocationTuple> locationTuple = service.findById(id);
        return locationTuple.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody LocationModel model) {
        return ResponseEntity.ok(service.save(model));
    }
}