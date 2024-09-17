package com.mari.reservemystay.model.reservation.implement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReserveDetailModel {
    private Long id;
    private Long reserveId;
    private Long personId;
}
