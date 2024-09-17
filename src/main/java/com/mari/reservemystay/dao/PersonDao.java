package com.mari.reservemystay.dao;


import com.mari.reservemystay.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonDao extends JpaRepository<Person, Long> {
    @Query(name = "PersonDao.checkPersonIdByMobileNo",nativeQuery = true)
    Integer isExistsPerson(String mobileNo);
    @Query(name = "PersonDao.findPersonIdByMobileNo",nativeQuery = true)
    Person findByMobileNo(String mobileNo);
}
