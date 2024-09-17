package com.mari.reservemystay.model.reservation.basic;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationModel {
    private Long id;
    private String name;
    private String code;
    private Long parentId;
}