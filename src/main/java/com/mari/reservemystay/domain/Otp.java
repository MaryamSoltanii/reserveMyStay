package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "sequence", sequenceName = "sq_otp", allocationSize = 1)
@Table(name = "tb_otp")
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    private Long id;

    @Column(name = "otp")
    private Integer otp;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "fk_usr")
    private Long userId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_used")
    private Boolean isUsed;
}
