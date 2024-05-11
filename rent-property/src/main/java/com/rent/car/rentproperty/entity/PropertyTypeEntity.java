package com.rent.car.rentproperty.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "property_type")
public class PropertyTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Nonnull
    @Column(length = 5)
    private String designation;


    public PropertyTypeEntity(@Nonnull String designation) {
        this.designation = designation;
    }


    public PropertyTypeEntity() {

    }
}
