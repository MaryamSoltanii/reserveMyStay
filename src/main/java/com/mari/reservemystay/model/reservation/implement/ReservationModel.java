package com.mari.reservemystay.model.reservation.implement;

import lombok.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReservationModel {
    private Long roomId;
    private Date fromDate;
    private Date toDate;
    private Date reserveDate;
    private Long userId;
    private List<Guestslist> guests;

    /*   {
        "roomId": 1,
            "fromDate": "2024-10-09",
            "endDate": "2024-10-15",
            "reserveDate": "2024-10-15",
            "guests": [
        {
            "nationalCode": "0020223803",
                "firstname": "مریم",
                "lastname": "سلطانی",
                "passportNo": "jsl4711",
                "fatherName": "قهرمان",
                "birthdate": "1997-01-01",
                "gender": "female",
                "mobileNo": "09388837308",
                "isLeader": 1
        },
        {
            "nationalCode": "0020223804",
                "firstname": "الهه",
                "lastname": "محمدی",
                "passportNo": "jsl4712",
                "fatherName": "سعید",
                "birthdate": "1995-05-21",
                "gender": "female",
                "mobileNo": "09121112233",
                "isLeader": 0
        }
  ]
    }*/
}
