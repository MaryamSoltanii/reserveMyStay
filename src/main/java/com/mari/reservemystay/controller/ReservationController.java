package com.mari.reservemystay.controller;

import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.reservation.implement.ReservationListByUser;
import com.mari.reservemystay.model.reservation.implement.ReservationModel;
import com.mari.reservemystay.services.reservation.implement.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ReservationController {
    @Autowired
    private ReserveService service;

    @PostMapping("/reserve")
    private ResponseEntity<Long> save(@RequestBody ReservationModel model) {
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

    @GetMapping("/reserve-list-by-user/user-id")
    private Optional<ReservationListByUser> getUserReserveList(@PathVariable Long userId){
        try {
            return service.getUserReserveList(userId);
        } catch (BusinessException e){
            throw new BusinessException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
