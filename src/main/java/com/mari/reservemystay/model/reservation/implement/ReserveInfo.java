package com.mari.reservemystay.model.reservation.implement;

import java.util.Date;

public interface ReserveInfo {
    String hotelName();
    Date fromDate();
    Date toDate();
    Date reservationDate();
}
