package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "sequence", sequenceName = "sq_user", allocationSize = 1)
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fk_prs")
    protected Long personId;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "is_active")
    private Integer isActive;
}
