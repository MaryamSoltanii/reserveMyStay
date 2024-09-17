package com.mari.reservemystay.model.reservation.basic;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelModel {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String englishName;
    private Long locationId;
}
