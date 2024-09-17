package com.mari.reservemystay.model.reservation.basic;

import com.mari.reservemystay.domain.Room;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomModel {
    private Long id;
    private Long hotelId;
    private Long commonData;
    private Long capacity;
    private Long price;
    private Date effectiveDate;
    private Date voidDate;
    private String description;
}
