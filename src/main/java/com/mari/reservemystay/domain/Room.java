package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "sequence_rom", sequenceName = "sq_room", allocationSize = 1)
@Table(name = "tb_room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_rom")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_htl")
    private Hotel HotelId;

    @ManyToOne
    @JoinColumn(name = "fk_cod")
    private CommonData commonData;

    @Column(name = "capacity")
    private Long capacity;

    @Column(name = "price")
    private Long price;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "void_date")
    private Date voidDate;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(HotelId, room.HotelId)
                && Objects.equals(commonData, room.commonData)
                && Objects.equals(capacity, room.capacity)
                && Objects.equals(price, room.price)
                && Objects.equals(effectiveDate, room.effectiveDate)
                && Objects.equals(voidDate, room.voidDate)
                && Objects.equals(description, room.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(HotelId, commonData, capacity, price, effectiveDate, voidDate, description);
    }
}
