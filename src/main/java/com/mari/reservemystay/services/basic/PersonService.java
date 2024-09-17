package com.mari.reservemystay.services.basic;

import com.mari.reservemystay.domain.Person;
import com.mari.reservemystay.model.basic.PersonModel;


public interface PersonService {

    Long save(PersonModel model);

    Long deletePersonById(Long id);

    Integer isExistsPerson(String mobileNo);

    Person findByMobileNo(String mobileNo);
}
