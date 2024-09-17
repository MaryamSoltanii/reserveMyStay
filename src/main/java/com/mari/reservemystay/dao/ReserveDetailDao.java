package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.ReserveDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveDetailDao extends JpaRepository<ReserveDetail,Long> {
}
