package com.mari.reservemystay.model.general;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpModel {
    private Long id;
    private Integer otp;
    private String ipAddress;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private Integer isUsed;
}
