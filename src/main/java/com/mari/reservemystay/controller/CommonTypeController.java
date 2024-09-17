package com.mari.reservemystay.controller;

import com.mari.reservemystay.model.basic.CommonTypeModel;
import com.mari.reservemystay.services.basic.CommonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common-type")
public class CommonTypeController {

    @Autowired
    private CommonTypeService service;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody CommonTypeModel model){
        return ResponseEntity.ok(service.save(model));
    }

}
