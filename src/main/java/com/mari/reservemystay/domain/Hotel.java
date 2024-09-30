package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@SequenceGenerator(name = "sequence_htl",sequenceName = "sq_hotel",allocationSize = 1)
@Table(name = "tb_hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "sequence_htl")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "english_name")
    private String english_name;

    @Column(name = "fk_loc")
    private Long location;
}
