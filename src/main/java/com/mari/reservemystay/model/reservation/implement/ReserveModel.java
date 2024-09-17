package com.mari.reservemystay.model.reservation.implement;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReserveModel {

    private Long id;
    private Long roomId;
    private Date fromDate;
    private Date toDate;
    private Integer isCancel;
    private Long isDelivery;
    private Date reserveDate;
    private Date cancelDate;
    private String code;
}
