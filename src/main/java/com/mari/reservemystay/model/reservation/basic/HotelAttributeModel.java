package com.mari.reservemystay.model.reservation.basic;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelAttributeModel {
    private Long id;
    private Long hotelId;
    private Long commonDataId;
    private Date effectiveDate;
    private Date voidDate;
}
