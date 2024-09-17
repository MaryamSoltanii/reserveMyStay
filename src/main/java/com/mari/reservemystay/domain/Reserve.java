package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "sequence", sequenceName = "sq_reserve", allocationSize = 1)
@Table(name = "tb_reserve")
public class Reserve {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_rom")
    private Room roomId;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "is_cancel")
    private Integer isCancel;

    @Column(name = "is_delivery")
    private Long isDelivery;

    @Column(name = "reserve_date")
    private Date reserveDate;

    @Column(name = "cancel_date")
    private Date cancelDate;

    @Column(name = "code")
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserve reserve = (Reserve) o;
        return Objects.equals(roomId, reserve.roomId)
                && Objects.equals(fromDate, reserve.fromDate)
                && Objects.equals(toDate, reserve.toDate)
                && Objects.equals(isCancel, reserve.isCancel)
                && Objects.equals(isDelivery, reserve.isDelivery);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roomId, fromDate, toDate, isCancel, isDelivery);

    }

}
