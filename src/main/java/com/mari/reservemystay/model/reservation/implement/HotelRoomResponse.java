package com.mari.reservemystay.model.reservation.implement;

import com.mari.reservemystay.model.reservation.basic.RoomList;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelRoomResponse {
    private String hotelName;
    private List<RoomList> room;
}
