package com.mari.reservemystay.services.basic;

import com.mari.reservemystay.dao.PersonDao;
import com.mari.reservemystay.domain.Person;
import com.mari.reservemystay.exception.BusinessException;
import com.mari.reservemystay.model.basic.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mari.reservemystay.exception.BusinessException.PRS_NOT_FOUND;


@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    public Long save(PersonModel model) {
        Person person = new Person();
        person.setNationalCode(model.getNationalCode());
        person.setFirstname(model.getFirstname());
        person.setLastname(model.getLastname());
        person.setFatherName(model.getFatherName());
        person.setPassportNo(model.getPassportNo());
        person.setGender(model.getGender());
        person.setBirthdate(model.getBirthdate());
        person.setMobileNo(model.getMobileNo());
        personDao.save(person);
        return person.getId();
    }
    public Long deletePersonById(Long id) {
        try {
            if (personDao.existsById(id)) {
                personDao.deleteById(id);
                return id;
            } else {
                throw new BusinessException(PRS_NOT_FOUND);
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Integer isExistsPerson(String mobileNo) {
        return personDao.isExistsPerson(mobileNo);
    }

    @Override
    public Person findByMobileNo(String mobileNo) {
        return personDao.findByMobileNo(mobileNo);
    }
}
