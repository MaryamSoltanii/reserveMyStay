package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "sequence_prs", sequenceName = "sq_person", allocationSize = 1)
@Table(name = "tb_person")

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_prs")
    private long id;

    @Column(name = "nationalcode")
    private String nationalCode;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "gender")
    private Long gender;

    @Column(name = "mobile_no")
    private String mobileNo;
}
