package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@SequenceGenerator(name = "sequence_cod" , sequenceName = "sq_common_data",allocationSize = 1)
@Table(name = "tb_common_data")
public class CommonData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "sequence_cod")
    private Long id;

    @Column(name = "fk_cot")
    private Long commonType;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

}

