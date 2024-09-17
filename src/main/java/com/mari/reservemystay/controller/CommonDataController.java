package com.mari.reservemystay.controller;

import com.mari.reservemystay.model.basic.CommonDataModel;
import com.mari.reservemystay.services.basic.CommonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common-data")
public class CommonDataController {

    @Autowired
    private CommonDataService service;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody CommonDataModel model){
        return ResponseEntity.ok(service.save(model));
    }
}
