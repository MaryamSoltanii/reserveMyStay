package com.mari.reservemystay.model.basic;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonDataModel {
    private Long id;
    private String code;
    private String name;
    private Long commonTypeId;
}
