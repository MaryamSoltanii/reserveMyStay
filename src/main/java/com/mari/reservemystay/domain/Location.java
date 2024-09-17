package com.mari.reservemystay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@SequenceGenerator(name = "sequence", sequenceName = "sq_location", allocationSize = 1)
@Table(name = "tb_location")
public class Location {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

//    @OneToOne
//    @Column(name = "name")
//    private Location parent;

    @Column(name = "parent_id")
    private Long parentId;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Hotel> hotels;

//    public Set<Hotel> getHotels(){
//        return getHotels();
//
//    }
}
