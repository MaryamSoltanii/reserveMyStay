package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.CommonData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonDataDao extends JpaRepository<CommonData, Long> {
}