package com.mari.reservemystay.model.basic;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonModel {
    private Long id;
    private String nationalCode;
    private String firstname;
    private String lastname;
    private String passportNo;
    private String fatherName;
    private Date birthdate;
    private Integer gender;
    private String mobileNo;
}
