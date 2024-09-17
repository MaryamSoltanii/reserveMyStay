package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "sequence_hot",sequenceName = "sq_hotel_attribute",allocationSize = 1)
@Table(name = "tb_hotel_attribute")
public class HotelAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "sequence_hot")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_htl")
    private Hotel fk_htl;

    @ManyToOne
    @JoinColumn(name = "fk_cod")
    private CommonData fk_cod;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "void_date")
    private Date voidDate;
}
