package com.mari.reservemystay.model.security;

import com.mari.reservemystay.domain.Person;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private Long id;
    private String username;
    private String password;
    private Long personId;
    private Date registerDate;
    private Integer isActive;

}
