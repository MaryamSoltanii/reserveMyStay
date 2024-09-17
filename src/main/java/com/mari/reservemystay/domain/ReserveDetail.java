package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_reserve_detail")
public class ReserveDetail {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_res")
    private Reserve reserveId;

    @ManyToOne
    @JoinColumn(name = "fk_prs")
    protected Person personId;
}
