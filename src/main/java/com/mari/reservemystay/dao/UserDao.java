package com.mari.reservemystay.dao;

import com.mari.reservemystay.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query(name = "UserDao.checkExistsUser", nativeQuery = true)
    Boolean isExistsUser(String username);

    @Query(name = "UserDao.checkActiveUser", nativeQuery = true)
    Long isActiveUser(String username);

    @Query("from User u where u.username=?1")
    User loadByUsername(String username);

    @Query(name = "UserDao.validateUserPass", nativeQuery = true)
    Integer ValidateUserPass(String username, String password);
}
