package com.mari.reservemystay.controller;

import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.reservation.implement.ReserveModel;
import com.mari.reservemystay.services.reservation.implement.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ReservationController {
    @Autowired
    private ReserveService service;

    @PostMapping("/reserve")
    private ResponseEntity<Long> save(@RequestBody ReserveModel model) {
        try {
            return ResponseEntity.ok(service.save(model));
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/unreserved/{reserveId}")
    private Long unreserved(@PathVariable Long reserveId) {
        try {
            return service.unreserved(reserveId);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
