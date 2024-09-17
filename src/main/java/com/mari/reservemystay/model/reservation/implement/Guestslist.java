package com.mari.reservemystay.model.reservation.implement;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Guestslist {
    private String NationalCode;
    private String Firstname;
    private String Lastname;
    private String PassportNo;
    private String FatherName;
    private Date Birthdate;
    private String Gender;
    private String MobileNo;
}
