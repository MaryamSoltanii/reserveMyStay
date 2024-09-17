package com.mari.reservemystay.controller;

import com.mari.reservemystay.model.basic.PersonModel;
import com.mari.reservemystay.services.basic.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody PersonModel model){
        return ResponseEntity.ok(service.save(model));

}
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePerson(@PathVariable Long id){
        return ResponseEntity.ok(service.deletePersonById(id));
    }
}

