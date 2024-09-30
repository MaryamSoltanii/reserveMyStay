package com.mari.reservemystay.model.reservation.implement;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationListByUser {
    private List<ReserveInfo> reserveInfo;
}
