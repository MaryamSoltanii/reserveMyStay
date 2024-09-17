package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.HotelAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelAttributeDao extends JpaRepository<HotelAttribute, Long> {

}