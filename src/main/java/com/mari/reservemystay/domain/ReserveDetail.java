package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "sequence", sequenceName = "sq_reserve_detail", allocationSize = 1)
@Table(name = "tb_reserve_detail")
public class ReserveDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    private Long id;

    @Column(name = "fk_res")
    private Long reserveId;

    @Column(name = "fk_prs")
    protected Long personId;
}
