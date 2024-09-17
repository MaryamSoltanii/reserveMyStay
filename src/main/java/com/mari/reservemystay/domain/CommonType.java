package com.mari.reservemystay.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "sequence_cot",sequenceName = "sq_common_type",allocationSize = 1)
@Table(name = "tb_common_type")
public class CommonType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "sequence_cot")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name= "name")
    private String name;

    @OneToMany(mappedBy = "commonType", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<CommonData> commonDataSet;

}
